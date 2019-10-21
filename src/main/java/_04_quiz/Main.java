package _04_quiz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws NullPointerException, FileNotFoundException {

        File fileOfUsersCategory = choosingCategory();

        List<QuizQuestion> questionsForCategory = howToGetOutQuestionsFromCategoryFile(fileOfUsersCategory);

        int score = 0;

        Random random = new Random();
        int questionIndex = random.nextInt(questionsForCategory.size());
        QuizQuestion quizQuestion = questionsForCategory.get(questionIndex);

        System.out.println(quizQuestion.getQuestion());
        List<String> answers = new ArrayList<String>(quizQuestion.getAnswers());
        String correctAnswer = answers.get(0);
        Collections.shuffle(answers);

        for (int i = 0; i < answers.size(); i++) {
            String answer = answers.get(i);
            System.out.println(i + "> " + answer);
        }

        System.out.println("Choose your answer:\n");
        Scanner scanner = new Scanner(System.in);
        String indexOfUsersAnswerString = scanner.nextLine();
        int indexOfUsersAnswer = Integer.parseInt(indexOfUsersAnswerString);
        String answerOfUser = answers.get(indexOfUsersAnswer);

        if (answerOfUser.equals(correctAnswer)) {
            System.out.println("You choose... wisely!");
            score++;
        } else {
            System.out.println("You choose... poorly");
        }


//        howToGetOutQuestionsFromCategoryFile(folderWithQuestions);

//
//
//
//        Scanner categorySelection = new Scanner(System.in);
//        String categorySelectionString = categorySelection.nextLine();
//
//        String[] categorySelectionTab = new String[files.length];
//
//        if (categorySelectionString.equals("all")) {
//            for (int i = 0; i < files.length; i++) {
//                categorySelectionTab[i] = filesWithQuestionsList.get(i);
//            }
//        } else {
//            for (int i = 0; i < files.length; i++) {
//                categorySelectionTab[i] = categorySelectionString.split(",");
//            }
//
//            // 1,2,8
//            //dostać z listy files 1-16 numery z categoryselectiontab
//
//        }
//
//        System.out.println("\nYou choose those categories: ");
//        for (int i = 0; i < categorySelectionTab.length; i++) {
//            System.out.println(filesWithQuestionsList.get(i));
//        }
//
//        howToGetOutQuestionsFromCategoryFile(folderWithQuestions);
//
//        System.out.println(questionsList.get(0).getQuestion());
//        System.out.println(questionsList.get(0).getAnswers());

    }

    private static File choosingCategory() {
        File folderWithQuestions = new File("src/main/resources/");
        File[] files = folderWithQuestions.listFiles();

        System.out.println("Choose number of category/all from this list:");

//        List<String> filesWithQuestionsList = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            File fileWithQuestions = files[i];
//            filesWithQuestionsList.add(i, fileWithQuestions.getName().replaceAll(".txt", ""));
            System.out.println(i + ". " + fileWithQuestions.getName());
        }

        System.out.println("----------------------------------------------------------"
                + "\nExamples of choosing category:"
                    + "\n5" + "\nall"
                    + "\n----------------------------------------------------------");

        Scanner scanner = new Scanner(System.in);
        String categorySelectionString = scanner.nextLine();

        int indexOfUsersCategory = Integer.parseInt(categorySelectionString);
        File fileOfUsersCategory = files[indexOfUsersCategory];


        System.out.println("\nYou choose this category: \n"
                + indexOfUsersCategory + ". "
                + fileOfUsersCategory.getName());

        System.out.println("Let's do this! :D");

        return fileOfUsersCategory;
    }

    private static List<QuizQuestion> howToGetOutQuestionsFromCategoryFile(File folderWithQuestions) throws FileNotFoundException {
        Scanner scanner = new Scanner(folderWithQuestions);

        List<QuizQuestion> questionsListForCategory = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String question = scanner.nextLine();
            int numberOfAnswers = Integer.parseInt(scanner.nextLine());

            List<String> answers = new ArrayList<>();

            for (int i = 0; i < numberOfAnswers; i++) {
                answers.add(i, scanner.nextLine());
            }

            questionsListForCategory.add(new QuizQuestion(question, answers));
        }
        return questionsListForCategory;
    }
}
