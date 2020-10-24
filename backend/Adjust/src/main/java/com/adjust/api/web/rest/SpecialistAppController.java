package com.adjust.api.web.rest;

import com.adjust.api.domain.*;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/specialist/app")
public class SpecialistAppController {
    private static class AccountResourceException extends RuntimeException {
        private AccountResourceException(String message) {
            super(message);
        }
    }

    private final Logger log = LoggerFactory.getLogger(AdjustClientResource.class);

    private static final String ENTITY_NAME = "adjustClient";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Value("${specialist.app.apiKey}")
    private String apiKey;

    private final UserService userService;

    private final TokenProvider tokenProvider;

    private final UserJWTController userJWTController;

    private final SpecialistRepository specialistRepository;

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
    private final NutritionService nutritionService;
    private final WorkoutService workoutService;
    private final ExerciseService exerciseService;

    private final NutritionRepository nutritionRepository;

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

    public SpecialistAppController(UserService userService, UserJWTController userJWTController, SpecialistRepository specialistRepository,
                                   TokenProvider tokenProvider, AdjustClientService adjustClientService,
                                   AdjustClientRepository adjustClientRepository, AdjustClientMapper adjustClientMapper,
                                   AdjustTokenResource adjustTokenResource, TutorialRepository tutorialRepository,
                                   TutorialService tutorialService, TutorialVideoService tutorialVideoService, TutorialMapper tutorialMapper,
                                   ShoppingItemService shoppingItemService, OrderService orderService,
                                   OrderItemService orderItemService, AdjustTokenService adjustTokenService,
                                   SpecialistService specialistService, SpecialistMapper specialistMapper, AdjustProgramService adjustProgramService, DevelopmentService developmentService,
                                   BodyCompositionService bodyCompositionService, FitnessProgramService fitnessProgramService, NutritionProgramService nutritionProgramService,
                                   MealService mealService, NutritionService nutritionService, NutritionRepository nutritionRepository, WorkoutService workoutService, ExerciseService exerciseService,
                                   DevelopmentMapper developmentMapper, BodyCompositionMapper bodyCompositionMapper, NutritionProgramMapper nutritionProgramMapper, FitnessProgramMapper fitnessProgramMapper, MealMapper mealMapper,
                                   NutritionMapper nutritionMapper, FoodMapper foodMapper, WorkoutMapper workoutMapper, ExerciseMapper exerciseMapper, MealsForNutritionMapper mealsForNutritionMapper, MoveMapper moveMapper,
                                   AdjustProgramRepository adjustProgramRepository, AdjustProgramMapper adjustProgramMapper, FoodRepository foodRepository, DevelopmentRepository developmentRepository,
                                   BodyCompositionRepository bodyCompositionRepository, ConversationService conversationService, ConversationRepository conversationRepository,
                                   ChatMessageRepository chatMessageRepository, ChatMessageMapper chatMessageMapper,
                                   ConversationMapper conversationMapper, AdjustPriceService adjustPriceService, MoveService moveService) {
        this.userService = userService;
        this.userJWTController = userJWTController;
        this.specialistRepository = specialistRepository;
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
        this.nutritionService = nutritionService;
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
    public ResponseEntity<UserJWTController.JWTToken> registerAccountBySpecialistApp(@Valid @RequestBody ManagedUserVM managedUserVM, @RequestHeader("Authorization") String authorization) throws Exception {
        if (!checkPasswordLength(managedUserVM.getPassword())) {
            throw new InvalidPasswordException();
        }

        String clientApiKeyEncoded = authorization.substring(6);
        log.info("specialist api key encoded: {}", clientApiKeyEncoded);

        String apiKeyEncoded = new String(Base64.getEncoder().encode(apiKey.getBytes()), "UTF-8");
        log.info("backend api key encoded: {}", apiKeyEncoded);
        if (!clientApiKeyEncoded.equals(apiKeyEncoded)) {
            throw new Exception("request does not come from specialist app");
        }

        managedUserVM.setLangKey("fa");
        com.adjust.api.domain.User user = userService.registerUser(managedUserVM, managedUserVM.getPassword(), false, true);


        SpecialistDTO specialistDTO = new SpecialistDTO();
        specialistDTO.setUsername(user.getLogin());
        specialistDTO.setBusy(false);
        specialistDTO.setStars(0.0);
        specialistService.save(specialistDTO);

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
    @GetMapping("/specialists")
    public ResponseEntity<SpecialistDTO> getSpecialistBySpecialistApp() {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        SpecialistDTO specialistDTO = specialistRepository.findByUsername(userLogin).map(specialistMapper::toDto).get();
        return ResponseEntity.ok().header("charset", "UTF-8")
            .header("charset", "UTF-8")
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, specialistDTO.getId().toString()))
            .body(specialistDTO);
    }

    /**
     * {@code PUT  /adjust-clients} : Updates an existing adjustClient from client app.
     *
     * @param specialistDTO the specialistDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adjustClientDTO,
     * or with status {@code 400 (Bad Request)} if the adjustClientDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adjustClientDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/specialists")
    public ResponseEntity<SpecialistDTO> updateAdjustClientByApp(@RequestBody SpecialistDTO specialistDTO) throws URISyntaxException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        SpecialistDTO specialistDTOUpdatee = specialistRepository.findByUsername(userLogin).map(specialistMapper::toDto).get();
        specialistDTO.setStars(0.0);
        SpecialistDTO specialistDTOUpdated = (SpecialistDTO) ClassUpdater.updateClass(specialistDTO, specialistDTOUpdatee);
        log.debug("REST request to update AdjustClient : {}", specialistDTOUpdated);
        if (specialistDTOUpdated.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SpecialistDTO result = specialistService.save(specialistDTOUpdated);
        return ResponseEntity.ok().header("charset", "UTF-8")
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, specialistDTOUpdated.getId().toString()))
            .body(result);
    }


    /**
     * {@code GET  /adjust-programs} : get all the adjustPrograms.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adjustPrograms in body.
     */
    @GetMapping("/adjust-programs")
    public List<DummyAdjustProgramDTO> getAllAdjustProgramsForSpecialistApp() {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        SpecialistDTO specialistDTO = specialistRepository.findByUsername(userLogin).map(specialistMapper::toDto).get();
        List<AdjustProgram> adjustPrograms = adjustProgramRepository.findAllBySpecialist(specialistMapper.toEntity(specialistDTO));
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

    @GetMapping("/adjust-nutritions")
    public ResponseEntity<List<DummyNutritionDTO>> getAdjustNutritionsForSpecialistApp() {
        List<Nutrition> adjustNutritions = nutritionRepository.findAll();
        List<DummyNutritionDTO> dummyNutritionDTOList = adjustNutritions.stream().map((adjustNutrition) -> {
            List<DummyFoodDTO> dummyFoodDTOList = adjustNutrition.getFoods().stream().map(adjustFood -> {
                DummyFoodDTO dummyFoodDTO = new DummyFoodDTO(foodMapper.toDto(adjustFood));
                return dummyFoodDTO;
            }).collect(Collectors.toList());
            DummyNutritionDTO dummyNutritionDTO = new DummyNutritionDTO(nutritionMapper.toDto(adjustNutrition));
            dummyNutritionDTO.setFoods(dummyFoodDTOList);
            return dummyNutritionDTO;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(dummyNutritionDTOList);
    }

    @GetMapping("/adjust-moves")
    public ResponseEntity<List<MoveDTO>> getAdjustMovesForSpecialistApp() {
        List<MoveDTO> moveDTOList = moveService.findAll();
        return ResponseEntity.ok(moveDTOList);
    }

    @PostMapping("/design-nutrition-program")
    public void designNutritionProgramBySpecialist(@RequestBody DummyAdjustProgramDTO dummyAdjustProgramDTO) {
        AdjustProgramDTO adjustProgramDTO = adjustProgramService.findOne(dummyAdjustProgramDTO.getId()).get();
        NutritionProgramDTO nutritionProgramDTO = nutritionProgramService.save(dummyAdjustProgramDTO.getNutritionProgram());
        dummyAdjustProgramDTO.getNutritionProgram().getMeals().forEach((mealDTO) -> {
            mealDTO.setNutritionProgramId(nutritionProgramDTO.getId());
            Set<NutritionDTO> nutritionDTOSet = new HashSet<NutritionDTO>(mealDTO.getNutritionList().stream().map(nutritionDTO -> nutritionDTO).collect(Collectors.toList()));
            mealDTO.setNutritions(nutritionDTOSet);
            mealService.save(mealDTO);
        });
        adjustProgramDTO.setNutritionProgramId(nutritionProgramDTO.getId());
        adjustProgramDTO.setNutritionDone(true);
        adjustProgramService.save(adjustProgramDTO);
    }

    @PostMapping("/design-fitness-program")
    public void designFitnessProgramBySpecialist(@RequestBody DummyAdjustProgramDTO dummyAdjustProgramDTO) {
        AdjustProgramDTO adjustProgramDTO = adjustProgramService.findOne(dummyAdjustProgramDTO.getId()).get();
        FitnessProgramDTO fitnessProgramDTO = fitnessProgramService.save(dummyAdjustProgramDTO.getFitnessProgram());
        dummyAdjustProgramDTO.getFitnessProgram().getWorkouts().forEach((dummyWorkoutDTO) -> {
            dummyWorkoutDTO.setFitnessProgramId(fitnessProgramDTO.getId());
            WorkoutDTO workoutDTO = workoutService.save(dummyWorkoutDTO);
            dummyWorkoutDTO.getExercises().forEach((dummyExerciseDTO) -> {
                dummyExerciseDTO.setWorkoutId(workoutDTO.getId());
                exerciseService.save(dummyExerciseDTO);
            });
        });
        adjustProgramDTO.setFitnessProgramId(fitnessProgramDTO.getId());
        adjustProgramDTO.setFitnessDone(true);
        adjustProgramService.save(adjustProgramDTO);
    }


    @GetMapping("/conversation")
    public ResponseEntity<DummyConversationDTO> getSpecialistMessages(@RequestParam("client-id") Long clientId, @RequestParam("specialist-id") Long specialistId) {
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
