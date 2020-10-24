// GENERATED FILE, do not edit!
import 'package:i18n/i18n.dart' as i18n;

String get _languageCode => 'en';
String get _localeName => 'en';

String _plural(int count, {String zero, String one, String two, String few, String many, String other}) =>
	i18n.plural(count, _languageCode, zero:zero, one:one, two:two, few:few, many:many, other:other);
String _ordinal(int count, {String zero, String one, String two, String few, String many, String other}) =>
	i18n.ordinal(count, _languageCode, zero:zero, one:one, two:two, few:few, many:many, other:other);
String _cardinal(int count, {String zero, String one, String two, String few, String many, String other}) =>
	i18n.cardinal(count, _languageCode, zero:zero, one:one, two:two, few:few, many:many, other:other);

class Translations {
	const Translations();
	MainTranslations get main => MainTranslations(this);
	ProgramTranslations get program => ProgramTranslations(this);
	UserTranslations get user => UserTranslations(this);
	CartTranslations get cart => CartTranslations(this);
	TutorialTranslations get tutorial => TutorialTranslations(this);
	MusclesTranslations get muscles => MusclesTranslations(this);
	OrderTranslations get order => OrderTranslations(this);
	Body_compositionTranslations get body_composition => Body_compositionTranslations(this);
	NutritionTranslations get nutrition => NutritionTranslations(this);
	FitnessTranslations get fitness => FitnessTranslations(this);
	MealsTranslations get meals => MealsTranslations(this);
	ProgressTranslations get progress => ProgressTranslations(this);
	SpecialistTranslations get specialist => SpecialistTranslations(this);
}

class MainTranslations {
	final Translations _parent;
	const MainTranslations(this._parent);
	String get ramsari => """دکتر سوسن رامسری""";
	String get adjust => """اجاست""";
	String get online_clinic => """کلینیک آنلاین تغذیه و ورزش اجاست""";
	String get score => """امتیاز""";
	String get token => """کوپن""";
	String get back => """برگرد""";
	String get ok => """تایید""";
	String get cancel => """لغو""";
	String get logout => """خروج""";
	String get buy => """پرداخت""";
	String get default_val => """پیش فرض""";
	String get update => """بروزرسانی""";
	String get tick_to_confirm => """در صورت موافقت با شرایط ذیل تیک بزنید""";
	String get pick_image => """انتخاب تصویر""";
	String get and => """و""";
	String get to => """به""";
	String get in_inside => """در""";
	String get till => """تا""";
	String get my => """من""";
	String get description => """توضیحات""";
	String get currency => """ریال""";
	String get phone_number => """شماره تلفن""";
	String get capture => """بگیر""";
	String get today => """امروز""";
	String get save => """ذخیره""";
	String get assess => """ارزیابی""";
	String get question => """سوال""";
	String get date => """تاریخ""";
	String get specialist => """متخصص""";
	String get client => """مراجع""";
	String get daily => """روزانه""";
	String get camera => """دوربین""";
	String get gallery => """گالری""";
	String get day => """روز""";
	String get month => """ماه""";
	String get year => """سال""";
	String get choose => """انتخاب""";
	String get degree => """درجه""";
	String get question_from_specialist => """سوال از متخصص""";
	String get free => """رایگان""";
	String empty_list(String s) => """لیست $s خالی میباشد.""";
	String get next => """بعدی""";
	String get previous => """قبلی""";
	String get done => """اتمام""";
}

class ProgramTranslations {
	final Translations _parent;
	const ProgramTranslations(this._parent);
	String get box_instruction => """برای دیدن برنامه های تغذیه و ورزشی به راست و برای ثبت و مشاهده ی پیشرفت و همچنین سوال از متخصص خود به راست بکشید""";
	String get fitness_nutrition_program => """برنامه تغذیه و ورزشی""";
	String get program => """برنامه""";
	String get programs => """برنامه ها""";
	String get fitness => """ورزش""";
	String get nutrition => """تغذیه""";
	String get not_ready => """برنامه شما طراحی نشده است!""";
	String get not_exist => """برنامه ای موجود نمی باشد""";
	String token_not_enough(String token) => """توکن شما کافی نیست. برای دریافت برنامه تغذیه و ورزشی به $token توکن نیاز دارید.""";
	String token_deducted(String token) => """برای دریافت برنامه تغذیه و ورزشی $token توکن از شما کسر خواهد شد.""";
	String get accept => """برای ادامه باید با شرایط شرح داده شده در پایین صفحه موافقت فرمایید.""";
}

class UserTranslations {
	final Translations _parent;
	const UserTranslations(this._parent);
	String get profile => """پروفایل""";
	String get login => """ورود""";
	String get forgotten_pass => """رمز عبور را فراموش کردم!""";
	String get register => """ثبت نام""";
	String get email => """ایمیل""";
	String get password => """رمز عبور""";
	String get password_repeat => """تکرار رمز عبور""";
	String get fill => """لطفا پر کنید""";
	String get choose => """لطفا انتخاب کنید""";
	String get wrong_email => """ایمیل اشتباه وارد شده""";
	String get wrong_password => """رمز عبور باید دارای حرف و عدد و بیش از 6 حرف باشد""";
	String get pass_not_match => """رمز عبور و تکرار آن یکسان نمیباشند""";
	String get registration_failed => """خطا در ثبت نام! لطفا از صحت عدم ثبت نام مجدد و اتصال اینترنت خود اطمینان حاصل فرمایید.""";
	String get login_failed => """خطا در ورود! لطفا از صحت نام کاربری و رمز عبور وارد شده اطمینان حاصل فرمایید.""";
	String get recover_password => """ایمیل خود را که با آن ثبت نام کرده اید وارد کرده و دکمه ی تایید را فشار دهید، سپس برای برای تغییر رمز عبور خود به ایمیل خود مراجعه کنید.""";
	String get success => """عملیات با موفقیت انجام شد!""";
	String get failure => """خطا در پردازش!""";
	String get set_to_default => """برگشت به حالت پیش فرض.""";
	String get sure_with_decision => """آیا از انتخاب خود اطمینان دارید؟""";
	String get sure_to_exit => """با خروج از برنامه برای شروع مجدد باید ایمیل و رمز عبور خود را مجدداً وارد کنید. آیا مایل به خروج از برنامه میباشید؟""";
	String get not_enough_token => """مقدار توکن کافی نمیباشد""";
	String get birth => """تاریخ تولد""";
	String get firstname => """نام""";
	String get lastname => """نام خانوادگی""";
	String get gender => """جنسیت""";
	String get age => """سن""";
	String get female => """زن""";
	String get male => """مرد""";
	String get choose_profile_picture => """انتخاب تصویر پروفایل""";
}

class CartTranslations {
	final Translations _parent;
	const CartTranslations(this._parent);
	String get cart => """سبد خرید""";
	String get empty => """سبد خرید شما خالی میباشد.""";
	String get add_to_cart => """افزودن به سبد خرید""";
	String get note_to_delete => """برای حذف کردن، هر ایتم را به طرفین بکشید.""";
	String get already_added => """محصول مورد نظر در سبد خرید موجود میباشد.""";
	String item_number(String number) => """تعداد موجودی این محصول $number عدد می باشد!""";
	String get number => """تعداد موجود""";
}

class TutorialTranslations {
	final Translations _parent;
	const TutorialTranslations(this._parent);
	String get tutorial => """آموزش""";
	String get course => """دوره""";
	String get courses => """دوره های آموزشی""";
	String get your_tutorial_num => """تعداد آموزش های شما""";
	String get has_tutorial => """شما این آموزش را دارید.""";
	String get videos => """ویدیوهای آموزشی""";
	String get video => """ویدیوی آموزشی""";
	String get not_enough_token => """مقدار توکن شما برای خرید درون برنامه این آموزش کافی نمیباشد!""";
	String token_deduction(String token) => """با تایید، مقدار $token کوپن جهت اتخاذ ویدیو و محتوای آموزشی، از شما کسر خواهد شد.""";
}

class MusclesTranslations {
	final Translations _parent;
	const MusclesTranslations(this._parent);
	String get chest => """سینه""";
	String get back => """پشت""";
	String get biceps => """جلوبازو""";
	String get triceps => """پشت بازو""";
	String get abs => """شکم""";
	String get calves => """ساق پا""";
	String get forearms => """ساعد""";
	String get glutes => """باسن""";
	String get hamstring => """پشت پا""";
	String get lateral => """زیر بغل""";
	String get trapezoid => """کول""";
	String get shoulder => """شانه""";
	String get leg => """پا""";
	String get aerobics => """هوازی""";
	String get trx => """حرکات با کش""";
	String get multimuscle => """حرکات چند عضله ای""";
	String get misc => """سایر عضلات""";
}

class OrderTranslations {
	final Translations _parent;
	const OrderTranslations(this._parent);
	String get phone_number => """شماره تلفن""";
	String get country => """کشور""";
	String get state => """استان""";
	String get city => """شهر""";
	String get address => """آدرس""";
	String get order => """سفارش""";
	String get orders => """سفارشات""";
	String get number => """تعداد""";
	String get price => """هزینه""";
	String get amount => """هزینه کل""";
	String get item_name => """نام محصول""";
}

class Body_compositionTranslations {
	final Translations _parent;
	const Body_compositionTranslations(this._parent);
	String get body_composition => """مشخصات جسمانی""";
	String get sure => """آیا اطلاعات وارد شده صحیح میباشد؟""";
	NotesBody_compositionTranslations get notes => NotesBody_compositionTranslations(this);
	String get anthropometry => """پیکرسنجی""";
	String get height => """قد""";
	String get weight => """وزن""";
	String get neck => """دور گردن""";
	String get wrist => """دور مچ""";
	String get waist => """دور کمر""";
	String get abdomen => """دور شکم""";
	String get hip => """دور لگن""";
	String get muscle_mass => """وزن عضله(muscle mass)""";
	String get fat_mass => """وزن چربی(fat mass)""";
	String get kilogram => """کیلوگرم""";
	String get centimeter => """سانتی متر""";
	String get body_image => """تصویر اندام از گردن به پایین""";
	String get body_composition_image => """تصویر بادی کامپوزیشن""";
	String get blood_test_image => """تصویر آزمایش خون""";
	String get terms => """توجه داشته باشید که ورودی هایی که علامت (*) دارند باید پر شوند ولی تمام ورودی ها نیاز به پر کردن نداشته ولی پر نکردن کامل مشخصات بدنی خود باعث کمبود دقت در برنامه های طراحی شده خواهد شد. در نتیجه مسوولیت عدم پر کردن کامل مشخصات بدنی به عهده ی خود شخص میباشد. ضمنا اعداد وارد شده برای مشخصات بدنی به صورت اعشار بوده (برای مثال 12.50 کیلوگرم برابر با 12 کیلو و پانصد گرم میباشد.)""";
	Workout_locationBody_compositionTranslations get workout_location => Workout_locationBody_compositionTranslations(this);
	GoalBody_compositionTranslations get goal => GoalBody_compositionTranslations(this);
	Activity_typeBody_compositionTranslations get activity_type => Activity_typeBody_compositionTranslations(this);
	Workout_typeBody_compositionTranslations get workout_type => Workout_typeBody_compositionTranslations(this);
	Body_typeBody_compositionTranslations get body_type => Body_typeBody_compositionTranslations(this);
}

class NotesBody_compositionTranslations {
	final Body_compositionTranslations _parent;
	const NotesBody_compositionTranslations(this._parent);
	String get first_page => """در صفحات بعدی شما بایستی مشخصات بدنی اعم از قد، وزن، دور مچ، دور گردن، دور کمر، دور شکم و دور لگن را با استفاده از یک سانتی متر اندازه گیری کرده و برای وارد کردن هر یک از مقدار های اندازه گیری شده، سانتی متر دیجیتالی پایین هر صفحه به چپ یا راست بکشید تا اندازه ی مورد نظر را نشان داده و سپس با استفاده از دکمه ی 'بعدی' به صفحه بعد رفته و مشخصه ی بعدی را وارد کنید. برای دقت بیشتر در وارد کردن مشخصه ها حتما هر اندازه گیری را با سانتی متر خود سه بار انجام داده و میانگین نتایج را در سانتی متر دیجیتال وارد کنید.""";
	String get height => """برای وارد کردن قد خود ابتدا به یک دیوار تکیه داده و نفس عمیق کشیده به طوری که شانه ها و باس به دیوار برخورد کنند. سپس سر خود را طوری نگه داشته که دقیقاً روبروی خور را مشاهده کنید. سپس از کسی کمک بخواهید که خطکشی بر روی سر شما به صورت کاملا عمود گذاشته و محل را علامت گذاری کند.""";
	String get weight => """وزن خود را میتوانید با یک ترازوی ساده به کیلوگرم در کیلوگرم دیجیتال پایین وارد کنید.""";
	String get neck => """شانه های خود را به عقب داده و سر را راست نگه داشته و دور گردن خود را از وسط با یک سانتی متر ساده اندازه گیری کرده و مقدار را به سانتی متر در سانتی متر دیجیتال پایین وارد کنید.""";
	String get wrist => """از قسمت استخانی مچ دست راست با یک سانتی متر ساده اندازه گیری کرده و مقدار را به سانتی متر در سانتی متر دیجیتال پایین وارد کنید.""";
	String get waist => """دور کوچک ترین ناحیه ی بین دنده ها (پایین زیر بغل) و ناف را با یک سانتی متر اندازه گیری کرده و نتیجه را در سانتی متر دیجیتال پایین وارد کنید. توجه کنید که مستقیم ایستاده و در ابتدای صحبت کردن (ابتدای خالی شدن نفس) مقدار را اندازی گیری کنید (به صورت خلاصه تنفس را آزاد کنید).""";
	String get abdomen => """اندازه ی دور شکم را از ناحیه ی ناف به سانتی متر اندازه گیری کرده و نتیجه را در سانتی متر دیجیتال پایین وارد کنید. توجه کنید که مستقیم ایستاده و در ابتدای صحبت کردن (ابتدای خالی شدن نفس) مقدار را اندازی گیری کنید (به صورت خلاصه تنفس را آزاد کنید).""";
	String get hip => """اندازه ی دور لگن خود را از بزرگترین ناحیه به سانتی متر اندازه گیری کرده و نتیجه را در سانتی متر دیجیتال پایین وارد کنید.""";
}

class Workout_locationBody_compositionTranslations {
	final Body_compositionTranslations _parent;
	const Workout_locationBody_compositionTranslations(this._parent);
	String get workout_location => """مکان تمرین""";
	String get gym => """باشگاه""";
	String get home => """خانه""";
}

class GoalBody_compositionTranslations {
	final Body_compositionTranslations _parent;
	const GoalBody_compositionTranslations(this._parent);
	String get goal => """هدف""";
	String get decrease_weight => """کاهش وزن""";
	String get increase_weight => """افزایش وزن""";
}

class Activity_typeBody_compositionTranslations {
	final Body_compositionTranslations _parent;
	const Activity_typeBody_compositionTranslations(this._parent);
	String get activity_type => """فعالیت روزانه""";
	String get sedentary => """بی تحرک""";
	String get light => """کم(قدم زدن)""";
	String get moderate => """متعادل (3 روز در هفته ورزش)""";
	String get high => """ورزش روزانه""";
	String get extra => """ورزش حرفه ای""";
}

class Workout_typeBody_compositionTranslations {
	final Body_compositionTranslations _parent;
	const Workout_typeBody_compositionTranslations(this._parent);
	String get workout_type => """مدل تمرین مد نظر""";
	String get powerlifting => """قدرتی""";
	String get bodybuilding => """بدنسازی حجمی""";
	String get bodybuilding_classic => """بدنسازی""";
	String get fitness => """تناسب اندام""";
	String get endurance => """ورزش استقامتی""";
	String get cardio => """ورزش هوازی""";
}

class Body_typeBody_compositionTranslations {
	final Body_compositionTranslations _parent;
	const Body_typeBody_compositionTranslations(this._parent);
	String get quiz_not_finished => """لطفاً ده سوال مربوط به تیپ بدنیتان را کامل کنید""";
	String get body_type => """تیپ بدنی""";
	String get body_status => """وضعیت بدن شما شبیه چه است؟""";
	Body_status_answersBody_typeBody_compositionTranslations get body_status_answers => Body_status_answersBody_typeBody_compositionTranslations(this);
	String get body_shape => """حالت بدن شما شبیه چه است؟""";
	Body_shape_answersBody_typeBody_compositionTranslations get body_shape_answers => Body_shape_answersBody_typeBody_compositionTranslations(this);
	String get childhood => """شما هنگام کودکی چگونه بودید؟""";
	Childhood_answersBody_typeBody_compositionTranslations get childhood_answers => Childhood_answersBody_typeBody_compositionTranslations(this);
	String get activity => """سطح فعالیت شما در کل چگونه است؟""";
	Activity_answersBody_typeBody_compositionTranslations get activity_answers => Activity_answersBody_typeBody_compositionTranslations(this);
	String get metabolism => """سوخت و ساز بدن شما چگونه است؟""";
	Metabolism_answersBody_typeBody_compositionTranslations get metabolism_answers => Metabolism_answersBody_typeBody_compositionTranslations(this);
	String get people_say => """مردم به شما میگویند؟""";
	People_say_answersBody_typeBody_compositionTranslations get people_say_answers => People_say_answersBody_typeBody_compositionTranslations(this);
	String get wrist => """اگر مچ دستتان را با دست دیگر بگیرید وضعیت انگشت میانی و شست چگونه است؟""";
	Wrist_answersBody_typeBody_compositionTranslations get wrist_answers => Wrist_answersBody_typeBody_compositionTranslations(this);
	String get weight => """در خصوص وزن بدن شما متوانید...""";
	Weight_answersBody_typeBody_compositionTranslations get weight_answers => Weight_answersBody_typeBody_compositionTranslations(this);
	String get hungry => """کی گرسنه هستید؟""";
	Hungry_answersBody_typeBody_compositionTranslations get hungry_answers => Hungry_answersBody_typeBody_compositionTranslations(this);
	String get people_perception => """مردم شما را چگونه توصیف میکنند؟""";
	People_perception_answersBody_typeBody_compositionTranslations get people_perception_answers => People_perception_answersBody_typeBody_compositionTranslations(this);
}

class Body_status_answersBody_typeBody_compositionTranslations {
	final Body_typeBody_compositionTranslations _parent;
	const Body_status_answersBody_typeBody_compositionTranslations(this._parent);
	String get a1 => """بدن چربی دار""";
	String get a2 => """بدن غضلانی یا گوشتی""";
	String get a3 => """بدن استخانی""";
}

class Body_shape_answersBody_typeBody_compositionTranslations {
	final Body_typeBody_compositionTranslations _parent;
	const Body_shape_answersBody_typeBody_compositionTranslations(this._parent);
	String get a1 => """گرد و لخت""";
	String get a2 => """چهارشانه و غضلانی""";
	String get a3 => """باریک و بلند""";
}

class Childhood_answersBody_typeBody_compositionTranslations {
	final Body_typeBody_compositionTranslations _parent;
	const Childhood_answersBody_typeBody_compositionTranslations(this._parent);
	String get a1 => """گوشتالو""";
	String get a2 => """نرمال و طبیعی""";
	String get a3 => """لاغر و ضعیف""";
}

class Activity_answersBody_typeBody_compositionTranslations {
	final Body_typeBody_compositionTranslations _parent;
	const Activity_answersBody_typeBody_compositionTranslations(this._parent);
	String get a1 => """بی تحرک""";
	String get a2 => """تقریباً فعال""";
	String get a3 => """بسیار فعال""";
}

class Metabolism_answersBody_typeBody_compositionTranslations {
	final Body_typeBody_compositionTranslations _parent;
	const Metabolism_answersBody_typeBody_compositionTranslations(this._parent);
	String get a1 => """کند و آهسته""";
	String get a2 => """سالم و طبیعی""";
	String get a3 => """بالا و سریع""";
}

class People_say_answersBody_typeBody_compositionTranslations {
	final Body_typeBody_compositionTranslations _parent;
	const People_say_answersBody_typeBody_compositionTranslations(this._parent);
	String get a1 => """باید وزنتان را کم کنید""";
	String get a2 => """فرم خوبی دارید""";
	String get a3 => """باید وزنتان را افزایش دهید""";
}

class Wrist_answersBody_typeBody_compositionTranslations {
	final Body_typeBody_compositionTranslations _parent;
	const Wrist_answersBody_typeBody_compositionTranslations(this._parent);
	String get a1 => """انگشت میانی و شست یکدیگر را لمس نمیکنند""";
	String get a2 => """انگشت میانی و شست یکدیگر را لمس میکنند""";
	String get a3 => """انگشت میانی و شست روی یکدیگر قرار میگیرند""";
}

class Weight_answersBody_typeBody_compositionTranslations {
	final Body_typeBody_compositionTranslations _parent;
	const Weight_answersBody_typeBody_compositionTranslations(this._parent);
	String get a1 => """وزنتان را راحت بالا ببرید، اما به سختی میتوانید وزنتان را کاهش دهید""";
	String get a2 => """وزنتان را راحت افزایش و یا کاهش دهید""";
	String get a3 => """مشکل اضافه کردن وزن را دارید""";
}

class Hungry_answersBody_typeBody_compositionTranslations {
	final Body_typeBody_compositionTranslations _parent;
	const Hungry_answersBody_typeBody_compositionTranslations(this._parent);
	String get a1 => """تقریبا همیشه""";
	String get a2 => """فقط زمان وعده های غذایی""";
	String get a3 => """بندرت و خیلی کم""";
}

class People_perception_answersBody_typeBody_compositionTranslations {
	final Body_typeBody_compositionTranslations _parent;
	const People_perception_answersBody_typeBody_compositionTranslations(this._parent);
	String get a1 => """شخصی احساساتی و پر هیجان""";
	String get a2 => """شخصی با فیزیولوژی مناسب""";
	String get a3 => """شخصی کم هیجان و پر فکر""";
}

class NutritionTranslations {
	final Translations _parent;
	const NutritionTranslations(this._parent);
	String get program => """برنامه ی تغذیه""";
	String get not_ready => """برنامه تغذیه ی شما طراحی نشده است!""";
	String get calory => """کالری""";
	String get content => """محتوا""";
	String get protein => """پروتئین""";
	String get fat => """چربی""";
	String get carbs => """کربوهیدرات""";
	String get gram => """گرم""";
	String get types => """انواع""";
	String get nutrition => """ماده غذایی""";
	String get unit => """تعداد واحد""";
	String get quantity_in_unit => """مقدار در واحد""";
	String get choose_nutrition => """انتخاب مواد غدایی برای برنامه""";
	String get choose_meal => """انتخاب وعده غذایی برای برنامه""";
	String get choose_unit => """انتخاب واحد برای مواد غذایی""";
	String get want_meal_removed => """آیا مایل به حذف وعده ی غذایی هستید؟""";
	String get atleast_one_nutrition => """لطفا حداقل یک ماده غذایی انتخاب کنید!""";
	String get atleast_one_meal => """لطفا حداقل یک وعده غذایی انتخاب کنید!""";
}

class FitnessTranslations {
	final Translations _parent;
	const FitnessTranslations(this._parent);
	String get program => """برنامه ی تمرینی""";
	String get not_ready => """برنامه ورزشی شما طراحی نشده است!""";
	String get sets => """ست ها""";
	String get set => """ست""";
	String get reps => """تعداد""";
	String get min_rep => """حداقل تعداد""";
	String get max_rep => """حداکثر تعداد""";
	String get move => """حرکت""";
	String get equipment => """تجهیزات""";
	String get muscle => """عضله""";
	String get quantity => """مقدار""";
	String get attribute => """مشخصه""";
	WorkoutFitnessTranslations get workout => WorkoutFitnessTranslations(this);
}

class WorkoutFitnessTranslations {
	final FitnessTranslations _parent;
	const WorkoutFitnessTranslations(this._parent);
	String get power => """قدرتی""";
	String get bodybuilding => """بدنسازی حجمی""";
	String get bodybuilding_classic => """بدنسازی""";
	String get fitness => """تناسب اندام""";
	String get endurance => """استقامتی""";
	String get cardio => """هوازی""";
	String get type => """مدل تمرین""";
	String get day_numbers => """تعداد روزهای تمرینی""";
	String get atleast_one_day => """حداقل باید یک روز تمرینی وارد کرد!""";
	String get type_empty => """مدل تمرین انتخاب نشده""";
	String get set_and_reps_number => """تعداد ست و تکرار""";
}

class MealsTranslations {
	final Translations _parent;
	const MealsTranslations(this._parent);
	String get breakfast => """صبحانه""";
	String get brunch => """صبحانه دوم""";
	String get lunch => """ناهار""";
	String get afternoonmeal => """عصرانه""";
	String get dinner => """شام""";
	String get evemeal => """وعده قبل از خواب""";
	String get beforetraining => """وعده قبل از ورزش""";
	String get aftertraining => """وعده بعد از ورزش""";
	String get midnightsupper => """صحری""";
	String get iftar => """افطار""";
	String get snack1 => """میان وعده اول""";
	String get snack2 => """میان وعده دوم""";
	String get snack3 => """میان وعده سوم""";
	String get snack4 => """میان وعده چهارم""";
	String get snack5 => """میان وعده پنجم""";
	String get snack6 => """میان وعده ششم""";
}

class ProgressTranslations {
	final Translations _parent;
	const ProgressTranslations(this._parent);
	String get today_nutrition_assessment => """ارزیابی تغذیه امروز""";
	String get today_fitness_assessment => """ارزیابی تمرین امروز""";
	String get fitness_assessment => """ارزیابی تمرینی""";
	String get nutrition_assessment => """ارزیابی تغذیه""";
	String get progress => """پیشرفت""";
	String get assessment_done_for_today => """شما برای امروز ارزیابی انجام داده اید""";
	String get note => """توجه داشته باشید در طول روز فقط یک بار میتوانید عملکرد خود را ارزیابی کرده و امتیاز دریافت کنید. پس ترجیحا در انتهای روز ارزیابی را انجام دهید. تلاش کنید که با خود نیز صادق بوده و ارزیابی روز خود را آنطور که میبینید انجام دهید.""";
}

class SpecialistTranslations {
	final Translations _parent;
	const SpecialistTranslations(this._parent);
	String get specialist => """متخصص""";
	String get specialists => """متخصصین""";
	String get field => """رشته""";
	String get resume => """رزومه""";
}

