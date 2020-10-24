import 'package:adjust_client/model/tutorial.dart';

final TutorialListState tutorialListStateInit = TutorialListState(List());
final ClientTutorialsState clientTutorialsStateInit =
    ClientTutorialsState(List());
final TutorialState tutorialStateInit =
    TutorialState(null, "", "", "", "", "", 0, 0, 0);

class ClientTutorialsState extends TutorialList {
  ClientTutorialsState(List<TutorialState> items) : super(items);
}

class TutorialListState extends TutorialList {
  TutorialListState(List<TutorialState> items) : super(items);
}

class TutorialState extends Tutorial {
  TutorialState(
      int id,
      String title,
      String description,
      String text,
      String thumbnail,
      String thumbnailContentType,
      double tokenPrice,
      int videoId,
      int courseId)
      : super(id, title, description, text, thumbnail, thumbnailContentType,
            tokenPrice, videoId, courseId);
}
