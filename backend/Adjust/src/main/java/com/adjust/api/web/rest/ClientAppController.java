package com.adjust.api.web.rest;

import com.adjust.api.domain.*;
import com.adjust.api.domain.enumeration.PurchaseOption;
import com.adjust.api.repository.*;
import com.adjust.api.security.SecurityUtils;
import com.adjust.api.security.jwt.TokenProvider;
import com.adjust.api.service.*;
import com.adjust.api.service.dto.*;
import com.adjust.api.service.mapper.*;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.web.rest.errors.InvalidPasswordException;
import com.adjust.api.web.rest.errors.LoginAlreadyUsedException;
import com.adjust.api.web.rest.vm.LoginVM;
import com.adjust.api.web.rest.vm.ManagedUserVM;
import io.github.jhipster.web.util.HeaderUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/client/app")
public class ClientAppController {

    private static class AccountResourceException extends RuntimeException {
        private AccountResourceException(String message) {
            super(message);
        }
    }

    private final Logger log = LoggerFactory.getLogger(AdjustClientResource.class);

    private static final String ENTITY_NAME = "adjustClient";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Value("${client.app.apiKey}")
    private String apiKey;

    private final UserService userService;

    private final TokenProvider tokenProvider;

    private final UserJWTController userJWTController;

    private final AdjustClientService adjustClientService;
    private final AdjustClientRepository adjustClientRepository;
    private final AdjustClientMapper adjustClientMapper;

    private final TutorialService tutorialService;
    private final TutorialVideoService tutorialVideoService;
    private final TutorialRepository tutorialRepository;
    private final TutorialMapper tutorialMapper;

    private final ShoppingItemService shoppingItemService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    private final AdjustTokenResource adjustTokenResource;
    private final AdjustTokenService adjustTokenService;

    private final SpecialistService specialistService;
    private final SpecialistMapper specialistMapper;

    private final AdjustProgramService adjustProgramService;
    private final DevelopmentService developmentService;
    private final BodyCompositionService bodyCompositionService;
    private final FitnessProgramService fitnessProgramService;
    private final NutritionProgramService nutritionProgramService;
    private final MealService mealService;
    private final NutritionRepository nutritionRepository;
    private final WorkoutService workoutService;
    private final ExerciseService exerciseService;

    private final AdjustProgramMapper adjustProgramMapper;
    private final DevelopmentMapper developmentMapper;
    private final BodyCompositionMapper bodyCompositionMapper;
    private final NutritionProgramMapper nutritionProgramMapper;
    private final FitnessProgramMapper fitnessProgramMapper;
    private final MealMapper mealMapper;
    private final NutritionMapper nutritionMapper;
    private final FoodMapper foodMapper;
    private final WorkoutMapper workoutMapper;
    private final ExerciseMapper exerciseMapper;
    private final MoveMapper moveMapper;
    private final MealsForNutritionMapper mealsForNutritionMapper;

    private final AdjustProgramRepository adjustProgramRepository;
    private final FoodRepository foodRepository;
    private final DevelopmentRepository developmentRepository;
    private final BodyCompositionRepository bodyCompositionRepository;


    private final ConversationService conversationService;
    private final ConversationRepository conversationRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageMapper chatMessageMapper;
    private final ConversationMapper conversationMapper;

    private final AdjustPriceService adjustPriceService;
    private final MoveService moveService;

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final ShoppingItemMapper shoppingItemMapper;

    private final MedicalProfileMapper medicalProfileMapper;

    private final CourseRepository courseRepository;
    private final CourseService courseService;
    private final CourseMapper courseMapper;

    public ClientAppController(UserService userService, UserJWTController userJWTController, TokenProvider tokenProvider, AdjustClientService adjustClientService,
                               AdjustClientRepository adjustClientRepository, AdjustClientMapper adjustClientMapper,
                               AdjustTokenResource adjustTokenResource, TutorialRepository tutorialRepository,
                               TutorialService tutorialService, TutorialVideoService tutorialVideoService, TutorialMapper tutorialMapper,
                               ShoppingItemService shoppingItemService, OrderService orderService,
                               OrderItemService orderItemService, AdjustTokenService adjustTokenService,
                               SpecialistService specialistService, SpecialistMapper specialistMapper, AdjustProgramService adjustProgramService, DevelopmentService developmentService,
                               BodyCompositionService bodyCompositionService, FitnessProgramService fitnessProgramService, NutritionProgramService nutritionProgramService,
                               MealService mealService, NutritionRepository nutritionRepository, WorkoutService workoutService, ExerciseService exerciseService,
                               DevelopmentMapper developmentMapper, BodyCompositionMapper bodyCompositionMapper, NutritionProgramMapper nutritionProgramMapper, FitnessProgramMapper fitnessProgramMapper, MealMapper mealMapper,
                               NutritionMapper nutritionMapper, FoodMapper foodMapper, WorkoutMapper workoutMapper, ExerciseMapper exerciseMapper, MealsForNutritionMapper mealsForNutritionMapper, MoveMapper moveMapper,
                               AdjustProgramRepository adjustProgramRepository, AdjustProgramMapper adjustProgramMapper, FoodRepository foodRepository, DevelopmentRepository developmentRepository,
                               BodyCompositionRepository bodyCompositionRepository, ConversationService conversationService, ConversationRepository conversationRepository,
                               ChatMessageRepository chatMessageRepository, ChatMessageMapper chatMessageMapper,
                               ConversationMapper conversationMapper, AdjustPriceService adjustPriceService, MoveService moveService, OrderMapper orderMapper,
                               OrderItemMapper orderItemMapper, ShoppingItemMapper shoppingItemMapper, MedicalProfileMapper medicalProfileMapper,
                               CourseRepository courseRepository, CourseService courseService, CourseMapper courseMapper) {
        this.userService = userService;
        this.userJWTController = userJWTController;
        this.tokenProvider = tokenProvider;
        this.adjustClientService = adjustClientService;
        this.adjustClientRepository = adjustClientRepository;
        this.adjustClientMapper = adjustClientMapper;
        this.tutorialRepository = tutorialRepository;
        this.tutorialService = tutorialService;
        this.tutorialVideoService = tutorialVideoService;
        this.tutorialMapper = tutorialMapper;
        this.shoppingItemService = shoppingItemService;
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.adjustTokenResource = adjustTokenResource;
        this.adjustTokenService = adjustTokenService;
        this.specialistService = specialistService;
        this.specialistMapper = specialistMapper;
        this.adjustProgramService = adjustProgramService;
        this.developmentService = developmentService;
        this.bodyCompositionService = bodyCompositionService;
        this.fitnessProgramService = fitnessProgramService;
        this.nutritionProgramService = nutritionProgramService;
        this.mealService = mealService;
        this.workoutService = workoutService;
        this.exerciseService = exerciseService;
        this.developmentMapper = developmentMapper;
        this.bodyCompositionMapper = bodyCompositionMapper;
        this.nutritionProgramMapper = nutritionProgramMapper;
        this.fitnessProgramMapper = fitnessProgramMapper;
        this.mealMapper = mealMapper;
        this.nutritionRepository = nutritionRepository;
        this.nutritionMapper = nutritionMapper;
        this.foodMapper = foodMapper;
        this.workoutMapper = workoutMapper;
        this.exerciseMapper = exerciseMapper;
        this.moveMapper = moveMapper;
        this.mealsForNutritionMapper = mealsForNutritionMapper;
        this.adjustProgramRepository = adjustProgramRepository;
        this.adjustProgramMapper = adjustProgramMapper;
        this.foodRepository = foodRepository;
        this.developmentRepository = developmentRepository;
        this.bodyCompositionRepository = bodyCompositionRepository;
        this.conversationService = conversationService;
        this.conversationRepository = conversationRepository;
        this.chatMessageRepository = chatMessageRepository;
        this.chatMessageMapper = chatMessageMapper;
        this.conversationMapper = conversationMapper;
        this.adjustPriceService = adjustPriceService;
        this.moveService = moveService;
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
        this.shoppingItemMapper = shoppingItemMapper;
        this.medicalProfileMapper = medicalProfileMapper;
        this.courseService = courseService;
        this.courseMapper = courseMapper;
        this.courseRepository = courseRepository;
    }

    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
            password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH &&
            password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
    }

    /**
     * {@code POST  /register} : register the user.
     *
     * @param managedUserVM the managed user View Model.
     * @throws com.adjust.api.web.rest.errors.InvalidPasswordException  {@code 400 (Bad Request)} if the password is incorrect.
     * @throws com.adjust.api.web.rest.errors.EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
     * @throws LoginAlreadyUsedException                                {@code 400 (Bad Request)} if the login is already used.
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserJWTController.JWTToken> registerAccountByClientApp(@Valid @RequestBody ManagedUserVM managedUserVM, @RequestHeader("Authorization") String authorization) throws Exception {
        if (!checkPasswordLength(managedUserVM.getPassword())) {
            throw new InvalidPasswordException();
        }

        String clientApiKeyEncoded = authorization.substring(6);
        log.info("client api key encoded: {}", clientApiKeyEncoded);

        String apiKeyEncoded = new String(Base64.getEncoder().encode(apiKey.getBytes()), "UTF-8");
        log.info("backend api key encoded: {}", apiKeyEncoded);
        if (!clientApiKeyEncoded.equals(apiKeyEncoded)) {
            throw new Exception("request does not come from client app");
        }

        managedUserVM.setLangKey("fa");
        com.adjust.api.domain.User user = userService.registerUser(managedUserVM, managedUserVM.getPassword(), true, false);


        AdjustClientDTO adjustClientDTO = new AdjustClientDTO();
        adjustClientDTO.setUsername(user.getLogin());
        adjustClientDTO.setToken(0.0);
        adjustClientDTO.setScore(0.0);
        adjustClientService.save(adjustClientDTO);

        LoginVM loginVM = new LoginVM();
        loginVM.setUsername(user.getLogin());
        loginVM.setPassword(managedUserVM.getPassword());
        loginVM.setRememberMe(true);
        return userJWTController.authorize(loginVM);
    }


    /**
     * get adjust client for login
     *
     * @return
     */
    @GetMapping("/adjust-clients")
    public ResponseEntity<DummyAdjustClientDTO> getAdjustClientByClientApp() {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        AdjustClient adjustClient = adjustClientRepository.findAdjustClientByUsername(userLogin).get();
        DummyAdjustClientDTO dummyAdjustClientDTO = new DummyAdjustClientDTO(adjustClientMapper.toDto(adjustClient));

        // set client medical profile
        MedicalProfile medicalProfile = adjustClient.getMedicalProfile();
        DummyMedicalProfileDTO dummyMedicalProfileDTO = null;
        if (medicalProfile != null) {
            dummyMedicalProfileDTO = new DummyMedicalProfileDTO(medicalProfileMapper.toDto(medicalProfile));
            BodyComposition bodyComposition = medicalProfile.getBodyComposition();
            DummyBodyCompositionDTO dummyBodyCompositionDTO = new DummyBodyCompositionDTO(bodyCompositionMapper.toDto(bodyComposition));
            dummyMedicalProfileDTO.setBodyComposition(dummyBodyCompositionDTO);
            dummyAdjustClientDTO.setMedicalProfile(dummyMedicalProfileDTO);
        }


        // set client orders
//        List<DummyOrderDTO> dummyOrderDTOList = new ArrayList<>();
//        Set<Order> adjustClientOrders = adjustClient.getOrders();
//        if (adjustClientOrders != null) {
//            dummyOrderDTOList = adjustClientOrders.stream().map(order -> {
//                DummyOrderDTO dummyOrderDTO = new DummyOrderDTO(orderMapper.toDto(order));
//                List<DummyOrderItemDTO> orderItemDTOList = order.getOrderItems().stream().map(orderItem -> {
//                    DummyOrderItemDTO dummyOrderItemDTO = new DummyOrderItemDTO(orderItemMapper.toDto(orderItem));
//                    DummyShoppingItemDTO dummyShoppingItemDTO = new DummyShoppingItemDTO(shoppingItemMapper.toDto(orderItem.getShoppingItem()));
//                    dummyShoppingItemDTO.setImage(null);
//                    dummyOrderItemDTO.setShoppingItem(dummyShoppingItemDTO);
//                    return dummyOrderItemDTO;
//                }).collect(Collectors.toList());
//                dummyOrderDTO.setOrderItems(orderItemDTOList);
//                return dummyOrderDTO;
//            }).collect(Collectors.toList());
//            dummyAdjustClientDTO.setOrders(dummyOrderDTOList);
//        }


        // set client tutorials
//        Set<Tutorial> adjustClientTutorials = adjustClient.getTutorials();
//        if (adjustClientTutorials != null) {
//            List<DummyTutorialDTO> dummyTutorialDTOList = adjustClientTutorials
//                .stream().map(tutorial -> {
//                    DummyTutorialDTO dummyTutorialDTO = new DummyTutorialDTO(tutorialMapper.toDto(tutorial));
//                    dummyTutorialDTO.setThumbnail(null);
//                    return dummyTutorialDTO;
//                }).collect(Collectors.toList());
//            dummyAdjustClientDTO.setTutorialList(dummyTutorialDTOList);
//        }


        return ResponseEntity.ok().header("charset", "UTF-8")
            .header("charset", "UTF-8")
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dummyAdjustClientDTO.getId().toString()))
            .body(dummyAdjustClientDTO);
    }

    @GetMapping("/get-client-orders")
    public ResponseEntity<List<DummyOrderDTO>> getTutorials() {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        AdjustClient adjustClient = adjustClientRepository.findAdjustClientByUsername(userLogin).get();
        // set client orders
        List<DummyOrderDTO> dummyOrderDTOList = new ArrayList<>();
        Set<Order> adjustClientOrders = adjustClient.getOrders();
        if (adjustClientOrders != null) {
            dummyOrderDTOList = adjustClientOrders.stream().map(order -> {
                DummyOrderDTO dummyOrderDTO = new DummyOrderDTO(orderMapper.toDto(order));
                List<DummyOrderItemDTO> orderItemDTOList = order.getOrderItems().stream().map(orderItem -> {
                    DummyOrderItemDTO dummyOrderItemDTO = new DummyOrderItemDTO(orderItemMapper.toDto(orderItem));
                    DummyShoppingItemDTO dummyShoppingItemDTO = new DummyShoppingItemDTO(shoppingItemMapper.toDto(orderItem.getShoppingItem()));
                    dummyShoppingItemDTO.setImage(null);
                    dummyOrderItemDTO.setShoppingItem(dummyShoppingItemDTO);
                    return dummyOrderItemDTO;
                }).collect(Collectors.toList());
                dummyOrderDTO.setOrderItems(orderItemDTOList);
                return dummyOrderDTO;
            }).collect(Collectors.toList());
        }
        return ResponseEntity.ok(dummyOrderDTOList);
    }

    /**
     * {@code PUT  /adjust-clients} : Updates an existing adjustClient from client app.
     *
     * @param adjustClientDTO the adjustClientDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adjustClientDTO,
     * or with status {@code 400 (Bad Request)} if the adjustClientDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adjustClientDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adjust-clients")
    public ResponseEntity<AdjustClientDTO> updateAdjustClientByApp(@RequestBody AdjustClientDTO adjustClientDTO) throws URISyntaxException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        AdjustClientDTO adjustClientDTOUpdatee = adjustClientRepository.findAdjustClientByUsername(userLogin).map(adjustClientMapper::toDto).get();
        adjustClientDTO.setScore(null);
        adjustClientDTO.setToken(null);
//        if (adjustClientDTO.getBirthDate() != null)
//            adjustClientDTO.setBirthDate(adjustClientDTO.getBirthDate().plusDays(1));
        AdjustClientDTO adjustClientDTOUpdated = (AdjustClientDTO) ClassUpdater.updateClass(adjustClientDTO, adjustClientDTOUpdatee);
        log.debug("REST request to update AdjustClient : {}", adjustClientDTOUpdated);
        if (adjustClientDTOUpdated.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdjustClientDTO result = adjustClientService.save(adjustClientDTOUpdated);
        return ResponseEntity.ok().header("charset", "UTF-8")
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adjustClientDTOUpdated.getId().toString()))
            .body(result);
    }

    /**
     * buy a video by client app
     *
     * @param videoId
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping("/buy-tutorial")
    public ResponseEntity<TutorialDTO> buyVideo(@RequestBody Long videoId, HttpServletResponse response) throws Exception {
        response.setHeader("charset", "utf-8");
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        AdjustClientDTO adjustClientDTO = adjustClientRepository.findAdjustClientByUsername(userLogin).map(adjustClientMapper::toDto).get();
        TutorialDTO adjustTutorialDTO = tutorialService.findOne(videoId).get();

        if (adjustClientDTO.getToken() < adjustTutorialDTO.getTokenPrice()) {
            ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("client does not have enough token");
        }

        List<TutorialDTO> tutorialDTOList = adjustClientRepository.findAdjustClientByUsername(userLogin).get()
            .getTutorials().stream().map(tutorial -> tutorialMapper.toDto(tutorial)).collect(Collectors.toList());

        boolean clientHasTutorial = tutorialDTOList.stream().filter((e) -> e.getVideoId() == adjustTutorialDTO.getVideoId()).collect(Collectors.toList()).iterator().hasNext();
        if (clientHasTutorial) {
            ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("client has the tutorial already");
        }

        Set<TutorialDTO> clientTutorials = adjustClientDTO.getTutorials();
        TutorialDTO tutorialDTO = tutorialService.findOne(videoId).get();
        clientTutorials.add(tutorialDTO);
        adjustClientDTO.setTutorials(clientTutorials);
        adjustClientService.save(adjustClientDTO);


        adjustClientDTO.setToken(adjustClientDTO.getToken() - adjustTutorialDTO.getTokenPrice());
        adjustClientService.save(adjustClientDTO);

        return ResponseEntity.ok().header("charset", "utf-8")
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adjustClientDTO.getId().toString()))
            .body(tutorialDTO);
    }

    /**
     * get the list of tutorials owned by client
     *
     * @return
     */
    @GetMapping("/get-tutorials")
    public ResponseEntity<List<TutorialDTO>> getClientTutorials() {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        List<TutorialDTO> tutorialDTOList = adjustClientRepository.findAdjustClientByUsername(userLogin).get()
            .getTutorials().stream().map(tutorial -> tutorialMapper.toDto(tutorial)).collect(Collectors.toList());
        return ResponseEntity.ok().header("charset", "utf-8")
            .body(tutorialDTOList);
    }

    /**
     * {@code GET  /courses} : get all the courses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of courses in body.
     */
    @GetMapping("/courses")
    public List<DummyCourseDTO> getAllCourses() {
        log.debug("REST request to get all Courses");
        List<DummyCourseDTO> dummyCourseDTOList = courseRepository.findAll().stream().map(course -> {
            DummyCourseDTO dummyCourseDTO = new DummyCourseDTO(courseMapper.toDto(course));
            List<DummyTutorialDTO> dummyTutorialDTOList = course.getTutorials().stream().map(tutorial -> {
                DummyTutorialDTO dummyTutorialDTO = new DummyTutorialDTO(tutorialMapper.toDto(tutorial));
                return dummyTutorialDTO;
            }).collect(Collectors.toList());
            dummyCourseDTO.setTutorials(dummyTutorialDTOList);
            return dummyCourseDTO;
        }).collect(Collectors.toList());
        return dummyCourseDTOList;
    }

    /**
     * broadcast tutorial video by videoId
     *
     * @param videoId
     * @param jwt
     * @return
     * @throws Exception
     */
    @GetMapping("/get-tutorial-video")
    public DeferredResult<ResponseEntity<ResourceRegion>> getClientTutorialVideo(@RequestParam("video-id") Long videoId, @RequestParam("jwt") String jwt, @RequestHeader HttpHeaders headers) throws Exception {
        String userLogin = ((User) tokenProvider.getAuthentication(jwt).getPrincipal()).getUsername();
        TutorialDTO tutorialDTO = tutorialService.findOne(videoId).get();
        boolean clientHasTutorial = false;
        if (tutorialDTO.getTokenPrice() != 0) {
            AdjustClientDTO adjustClientDTO = adjustClientRepository.findAdjustClientByUsername(userLogin).map(adjustClientMapper::toDto).get();
            clientHasTutorial = adjustClientDTO.getTutorials().stream()
                .filter(tutorial -> tutorial.getId() == videoId).collect(Collectors.toList()).size() > 0;
            if (!clientHasTutorial) {
                throw new Exception("client has not bought the tutorial");
            }
        }
        TutorialVideoDTO tutorialVideoDTO = tutorialVideoService.findOne(tutorialDTO.getVideoId()).get();

        byte[] videoByte = tutorialVideoDTO.getContent();
        ByteArrayResource resource = new ByteArrayResource(videoByte);

        ResourceRegion region = region(resource, headers);

        DeferredResult<ResponseEntity<ResourceRegion>> dr = new DeferredResult<>();
        dr.setResult(ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(region));
        return dr;
    }


    private ResourceRegion region(ByteArrayResource video, HttpHeaders headers) {
        try {
            long contentLength = video.contentLength();
            try {
                HttpRange range = headers.getRange().get(0);
                long start = range.getRangeStart(contentLength);
                long end = range.getRangeEnd(contentLength);
                return new ResourceRegion(video, start, contentLength);
            } catch (Exception e) {
                return new ResourceRegion(video, 0, contentLength);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * {@code GET  /shopping-items} : get all the shoppingItems.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of shoppingItems in body.
     */
    @GetMapping("/shopping-items")
    public List<ShoppingItemDTO> getAllShoppingItemsForClientApp() {
        log.debug("REST request to get all ShoppingItems");
        return shoppingItemService.findAll().stream().filter(i -> i.isOrderable() && i.getNumber() > 0).collect(Collectors.toList());
    }


    /**
     * order a product from client app using DummyOrderDTO class
     *
     * @param dummyOrderDTO
     * @return
     */
    @PostMapping("/order")
    public ResponseEntity<DummyOrderDTO> order(@RequestBody DummyOrderDTO dummyOrderDTO) {

        OrderDTO orderDTO = orderService.save(dummyOrderDTO);
        dummyOrderDTO.getOrderItems().forEach((dummyOrderItemDTO) -> {
            dummyOrderItemDTO.setOrderId(orderDTO.getId());
            dummyOrderItemDTO.setShoppingItemId(dummyOrderItemDTO.getShoppingItem().getId());
            ShoppingItemDTO shoppingItemDTO = shoppingItemService.findOne(dummyOrderItemDTO.getShoppingItem().getId()).get();
            // deduct one from shopping item number of items if ordered one
            shoppingItemDTO.setNumber(shoppingItemDTO.getNumber() != 0 ? shoppingItemDTO.getNumber() - dummyOrderItemDTO.getNumber() : 0);
            shoppingItemService.save(shoppingItemDTO);
            orderItemService.save(dummyOrderItemDTO);
        });
        return ResponseEntity.ok(dummyOrderDTO);
    }

    /**
     * buy a token for client by client app
     *
     * @param adjustTokenItemId
     * @return
     */
    @PostMapping("/buy-token")
    public ResponseEntity<Double> buyToken(@RequestBody String adjustTokenItemId) {
        AdjustTokenDTO adjustTokensDTO = adjustTokenResource.getAdjustToken(Long.valueOf(adjustTokenItemId)).getBody();
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        AdjustClientDTO adjustClientDTO = adjustClientRepository.findAdjustClientByUsername(userLogin).map(adjustClientMapper::toDto).get();
        adjustClientDTO.setToken(adjustClientDTO.getToken() + adjustTokensDTO.getToken());
        AdjustClientDTO result = adjustClientService.save(adjustClientDTO);
        return ResponseEntity.ok(result.getToken());
    }

    /**
     * get the amount of token client has
     *
     * @return
     */
    @GetMapping("/get-client-token")
    public ResponseEntity<Double> getClientTokens() {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        AdjustClientDTO adjustClientDTO = adjustClientRepository.findAdjustClientByUsername(userLogin).map(adjustClientMapper::toDto).get();
        return ResponseEntity.ok(adjustClientDTO.getToken());
    }

    /**
     * {@code GET  /adjust-tokens} : get all the adjustTokens for client app.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adjustTokens in body.
     */
    @GetMapping("/adjust-tokens")
    public List<AdjustTokenDTO> getAllAdjustTokensForClientApp() {
        log.debug("REST request to get all AdjustTokens");
        return adjustTokenService.findAll();
    }

    /**
     * {@code GET  /adjust-tutorials} : get all the adjustTutorials.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adjustTutorials in body.
     */
    @GetMapping("/adjust-tutorials")
    public List<TutorialDTO> getAllAdjustTutorialsForClientApp() {
        log.debug("REST request to get all AdjustTutorials");
        return tutorialService.findAll();
    }

    /**
     * {@code GET  /specialists} : get all the specialists.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of specialists in body.
     */
    @GetMapping("/specialists")
    public List<SpecialistDTO> getAllSpecialists() {
        log.debug("REST request to get all Specialists");
        return specialistService.findAll();
    }

    @GetMapping("/program-price")
    public ResponseEntity<Double> getProgramPrice() {
        List<AdjustPriceDTO> adjustPriceDTOList = adjustPriceService.findAll();
        for (AdjustPriceDTO adjustPriceDTO : adjustPriceDTOList) {
            if (adjustPriceDTO.getOption() == PurchaseOption.PROGRAM) {
                return ResponseEntity.ok(adjustPriceDTO.getToken());
            }
        }
        return ResponseEntity.ok(1000.0);
    }

    //program request

    /**
     * reuquest program by client app
     *
     * @param dummyAdjustProgramDTO
     * @return
     */
    @PostMapping("/request-program")
    public ResponseEntity<AdjustProgramDTO> requestForProgramByClient(@RequestBody DummyAdjustProgramDTO dummyAdjustProgramDTO) throws Exception {
        // set client id for program
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        AdjustClientDTO adjustClientDTO = adjustClientRepository.findAdjustClientByUsername(userLogin).map(adjustClientMapper::toDto).get();

        boolean hasEnoughToken = false;
        List<AdjustPriceDTO> adjustPriceDTOList = adjustPriceService.findAll();
        for (AdjustPriceDTO adjustPriceDTO : adjustPriceDTOList) {
            if (adjustPriceDTO.getOption() == PurchaseOption.PROGRAM) {
                hasEnoughToken = adjustClientDTO.getToken() >= adjustPriceDTO.getToken();
            }
        }

        if (!hasEnoughToken) {
            throw new Exception("not enough token for program purchase!");
        }

        // set adjust program body composition
        dummyAdjustProgramDTO.setAdjustClientId(adjustClientDTO.getId());
        BodyCompositionDTO bodyCompositionDTO = dummyAdjustProgramDTO.getBodyComposition();
        bodyCompositionDTO = bodyCompositionService.save(bodyCompositionDTO);
        dummyAdjustProgramDTO.setBodyCompositionId(bodyCompositionDTO.getId());
        AdjustProgramDTO adjustProgramDTO = adjustProgramService.save(dummyAdjustProgramDTO);

        ConversationDTO conversationDTO = new ConversationDTO();
        conversationDTO = conversationService.save(conversationDTO);
        conversationDTO.setSpecialistId(dummyAdjustProgramDTO.getSpecialistId());
        conversationDTO.setAdjustClientId(dummyAdjustProgramDTO.getAdjustClientId());
        conversationService.save(conversationDTO);


        return ResponseEntity.ok().header("charset", "utf-8").body(adjustProgramDTO);
    }

    /**
     * {@code GET  /adjust-programs} : get all the adjustPrograms.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adjustPrograms in body.
     */
    @GetMapping("/adjust-programs")
    public List<DummyAdjustProgramDTO> getAllAdjustPrograms() {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        AdjustClientDTO adjustClientDTO = adjustClientRepository.findAdjustClientByUsername(userLogin).map(adjustClientMapper::toDto).get();
        List<AdjustProgram> adjustPrograms = adjustProgramRepository.findAllByAdjustClient(adjustClientMapper.toEntity(adjustClientDTO));
        List<DummyAdjustProgramDTO> adjustProgramDTOList = adjustPrograms.stream().map((program) -> {

            // set adjust program's client
            AdjustClient adjustClient = program.getAdjustClient();
            DummyAdjustClientDTO dummyAdjustClientDTO = new DummyAdjustClientDTO(adjustClientMapper.toDto(adjustClient));

            // set adjust program's specialist
            Specialist specialist = program.getSpecialist();
            DummySpecialistDTO dummySpecialistDTO = new DummySpecialistDTO(specialistMapper.toDto(specialist));

            // set adjust program's program development
            List<DummyDevelopmentDTO> dummyDevelopmentDTOList = developmentRepository.findAllByAdjustProgram(program).stream().map((programDevelopment) -> {
                DummyDevelopmentDTO dummyProgramDevelopmentDTO = new DummyDevelopmentDTO(developmentMapper.toDto(programDevelopment));
                return dummyProgramDevelopmentDTO;
            }).collect(Collectors.toList());

            // set adjust program's body composition
            DummyBodyCompositionDTO dummyBodyCompositionDTO = new DummyBodyCompositionDTO(bodyCompositionMapper.toDto(program.getBodyComposition()));

            // set adjust program's nutrition program
            NutritionProgram nutritionProgram = program.getNutritionProgram();
            DummyNutritionProgramDTO dummyNutritionProgramDTO = new DummyNutritionProgramDTO();
            if (nutritionProgram != null) {
                dummyNutritionProgramDTO = new DummyNutritionProgramDTO(nutritionProgramMapper.toDto(nutritionProgram));
                // get meals
                List<DummyMealDTO> dummyMealDTOList = nutritionProgram.getMeals().stream().map(meal -> {
                    DummyMealDTO dummyMealDTO = new DummyMealDTO(mealMapper.toDto(meal));
                    // get nutritions for a meal
                    List<DummyNutritionDTO> dummyNutritionDTOList = meal.getNutritions().stream().map(nutrition -> {
                        DummyNutritionDTO dummyNutritionDTO = new DummyNutritionDTO(nutritionMapper.toDto(nutrition));
                        // get foods for a nutrition
                        List<DummyFoodDTO> dummyFoodDTOList = nutrition.getFoods()
                            .stream().map(food ->  new DummyFoodDTO(foodMapper.toDto(food))).collect(Collectors.toList());
                        // get meal types for a nutrition
                        List<DummyMealsForNutritionDTO> dummyMealsForNutritionDTOList = nutrition.getMealTypes()
                            .stream().map(mealType -> new DummyMealsForNutritionDTO(mealsForNutritionMapper.toDto(mealType)))
                            .collect(Collectors.toList());
                        dummyNutritionDTO.setFoods(dummyFoodDTOList);
                        dummyNutritionDTO.setMealTypes(dummyMealsForNutritionDTOList);
                        return dummyNutritionDTO;
                    }).collect(Collectors.toList());
                    dummyMealDTO.setNutritionList(dummyNutritionDTOList);
                    return dummyMealDTO;
                }).collect(Collectors.toList());
                dummyNutritionProgramDTO.setMeals(dummyMealDTOList);
            }

            // set adjust programs's fitness program
            FitnessProgram fitnessProgram = program.getFitnessProgram();
            DummyFitnessProgramDTO dummyFitnessProgramDTO = new DummyFitnessProgramDTO();
            if (fitnessProgram != null) {
                dummyFitnessProgramDTO = new DummyFitnessProgramDTO(fitnessProgramMapper.toDto(fitnessProgram));
                // get workouts for fitness program
                List<DummyWorkoutDTO> dummyWorkoutDTOList = fitnessProgram.getWorkouts().stream().map(workout -> {
                    DummyWorkoutDTO dummyWorkoutDTO = new DummyWorkoutDTO(workoutMapper.toDto(workout));
                    // get exercises for workout
                    List<DummyExerciseDTO> dummyExerciseDTOList = workout.getExercises().stream().map(exercise -> {
                        DummyExerciseDTO dummyExerciseDTO = new DummyExerciseDTO(exerciseMapper.toDto(exercise));
                        // get move for exercise
                        DummyMoveDTO dummyMoveDTO = new DummyMoveDTO(moveMapper.toDto(exercise.getMove()));
                        dummyExerciseDTO.setMove(dummyMoveDTO);
                        return dummyExerciseDTO;
                    }).collect(Collectors.toList());
                    dummyWorkoutDTO.setExercises(dummyExerciseDTOList);
                    return dummyWorkoutDTO;
                }).collect(Collectors.toList());
                dummyFitnessProgramDTO.setWorkouts(dummyWorkoutDTOList);
            }

            DummyAdjustProgramDTO dummyAdjustProgramDTO = new DummyAdjustProgramDTO(adjustProgramMapper.toDto(program));
            dummyAdjustProgramDTO.setAdjustClient(dummyAdjustClientDTO);
            dummyAdjustProgramDTO.setSpecialist(dummySpecialistDTO);
            dummyAdjustProgramDTO.setDevelopments(dummyDevelopmentDTOList);
            dummyAdjustProgramDTO.setBodyComposition(dummyBodyCompositionDTO);
            dummyAdjustProgramDTO.setNutritionProgram(dummyNutritionProgramDTO);
            dummyAdjustProgramDTO.setFitnessProgram(dummyFitnessProgramDTO);

            return dummyAdjustProgramDTO;
        }).collect(Collectors.toList());
        log.debug("REST request to get all AdjustPrograms");
        return adjustProgramDTOList;
    }

    @PostMapping("/program-developments")
    public List<DevelopmentDTO> rate(@RequestBody DummyDevelopmentDTO developmentDTO) throws Exception {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        AdjustClientDTO adjustClientDTO = adjustClientRepository.findAdjustClientByUsername(userLogin).map(adjustClientMapper::toDto).get();
        LocalDate localDate = LocalDate.now();
        AdjustProgram adjustProgram = adjustProgramRepository.findById(developmentDTO.getAdjustProgramId()).get();
        for (Development pd : adjustProgram.getDevelopments()) {
            if (pd.getDate().getYear() == localDate.getYear() && pd.getDate().getMonth() == localDate.getMonth() && pd.getDate().getDayOfMonth() == localDate.getDayOfMonth()) {
                throw new Exception("client has scored his/her performance already today!");
            }
        }
        developmentDTO.setDate(localDate);
        if (developmentDTO.getFitnessScore() <= 5 && developmentDTO.getNutritionScore() <= 5) {
            adjustClientDTO.setScore(adjustClientDTO.getScore() + developmentDTO.getNutritionScore() + developmentDTO.getFitnessScore());
            adjustClientService.save(adjustClientDTO);
            developmentService.save(developmentDTO);
            List<DevelopmentDTO> programDevelopmentDTOList =
                developmentRepository.findAllByAdjustProgram(adjustProgram).stream().map((development -> developmentMapper.toDto(development))).collect(Collectors.toList());
            return programDevelopmentDTOList;
        } else {
            throw new Exception("wrong score!");
        }
    }


    @GetMapping("/client-score")
    public ResponseEntity<Double> getClientScore() {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        AdjustClientDTO adjustClientDTO = adjustClientRepository.findAdjustClientByUsername(userLogin).map(adjustClientMapper::toDto).get();
        return ResponseEntity.ok(adjustClientDTO.getScore());
    }

    @GetMapping("/conversation")
    public ResponseEntity<DummyConversationDTO> getClientMessages(@RequestParam("client-id") Long clientId, @RequestParam("specialist-id") Long specialistId) {
        AdjustClient adjustClient = adjustClientMapper.toEntity(adjustClientService.findOne(clientId).get());
        Specialist specialist = specialistMapper.toEntity(specialistService.findOne(specialistId).get());
        Conversation conversation = conversationRepository.findByAdjustClientAndSpecialist(adjustClient, specialist);
        DummyConversationDTO dummyConversationDTO = new DummyConversationDTO(conversationMapper.toDto(conversation));
        List<DummyChatMessageDTO> dummyChatMessageDTOList = conversation.getMessages().stream().map(chatMessage -> {
            DummyChatMessageDTO dummyChatMessageDTO = new DummyChatMessageDTO(chatMessageMapper.toDto(chatMessage));
            return dummyChatMessageDTO;
        }).collect(Collectors.toList());
        dummyConversationDTO.setMessages(dummyChatMessageDTOList);
        return ResponseEntity.ok(dummyConversationDTO);
    }
}
