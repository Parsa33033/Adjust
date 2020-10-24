
import 'enum/gender.dart';

class Client {

  int id;

  String username;

  String firstName;

  String lastName;

  DateTime birthDate;

  int age;

  Gender gender;

  double token;

  double score;

  String image;

  String imageContentType;

  int medicalProfileId;

  Client(
      this.id,
      this.username,
      this.firstName,
      this.lastName,
      this.birthDate,
      this.age,
      this.gender,
      this.token,
      this.score,
      this.image,
      this.imageContentType,
      this.medicalProfileId);
}

