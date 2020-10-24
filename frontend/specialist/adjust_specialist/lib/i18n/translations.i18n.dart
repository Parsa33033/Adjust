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
	String get questions => """سوالات""";
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
	String get message => """افزودن پیام""";
	String get yes => """بله""";
	String get no => """خیر""";
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
}

class TutorialTranslations {
	final Translations _parent;
	const TutorialTranslations(this._parent);
	String get tutorial => """آموزش""";
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
}

class Body_compositionTranslations {
	final Translations _parent;
	const Body_compositionTranslations(this._parent);
	String get height => """قد""";
	String get weight => """وزن""";
	String get wrist => """دور مچ""";
	String get waist => """دور کمر""";
	String get muscle_mass => """وزن عضله(muscle mass)""";
	String get fat_mass => """وزن چربی(fat mass)""";
	String get kilogram => """کیلوگرم""";
	String get centimeter => """سانتی متر""";
	String get body_image => """تصویر اندام از گردن به پایین""";
	String get body_composition_image => """تصویر بادی کامپوزیشن""";
	String get blood_test_image => """تصویر آزمایش خون""";
	String get medical_profile => """پرونده پزشکی""";
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
	String get protein_percentage => """درصد پروتئین""";
	String get fat_percentage => """درصد چربی""";
	String get carbs_percentage => """درصد کربوهیدرات""";
	String get gram => """گرم""";
	String get types => """انواع""";
	String get nutrition => """ماده غذایی""";
	String get unit => """واحد""";
	String get quantity_in_unit => """مقدار در واحد""";
	String get choose_nutrition => """انتخاب مواد غدایی برای برنامه""";
	String get choose_meal => """انتخاب وعده غذایی برای برنامه""";
	String get choose_unit => """انتخاب واحد برای مواد غذایی""";
	String get want_meal_removed => """آیا مایل به حذف وعده ی غذایی هستید؟""";
	String get atleast_one_nutrition => """لطفا حداقل یک ماده غذایی انتخاب کنید!""";
	String get atleast_one_meal => """لطفا حداقل یک وعده غذایی انتخاب کنید!""";
	String get addition_must_be_100 => """مجموع درصد های پروتئین، کربوهیدات و چربی باید 100 باشد""";
	String get fill_percentage => """لطفا درصد های پروتئین، کربوهیدات و چربی را پر کنید""";
	String get fill_calory => """لطفا مقدار کالری را پر کنید""";
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
	String get press_to_move => """برای تغییر در ترتیب حرکات بر روی حرکت به صورت طولانی فشار داده و سپس حرکت دهید!""";
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
}

class SpecialistTranslations {
	final Translations _parent;
	const SpecialistTranslations(this._parent);
	String get specialist => """متخصص""";
	String get specialists => """متخصصین""";
	String get field => """رشته""";
	String get resume => """رزومه""";
	String get busy => """مشغول""";
	String get degree => """درجه تحصیلی""";
}

