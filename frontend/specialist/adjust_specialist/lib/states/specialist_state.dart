import 'package:adjust_specialist/model/specialist.dart';
import 'package:adjust_specialist/model/enum/gender.dart';

final SpecialistState specialistStateInit = SpecialistState(null, "", "", "", DateTime(2000), null, "", "", "", 0, null, "", false);

class SpecialistState extends Specialist {
  SpecialistState(
      int id,
      String username,
      String firstName,
      String lastName,
      DateTime birthday,
      Gender gender,
      String degree,
      String field,
      String resume,
      double stars,
      String image,
      String imageContentType,
      bool busy)
      : super(id, username, firstName, lastName, birthday, gender, degree,
            field, resume, stars, image, imageContentType, busy);
}
