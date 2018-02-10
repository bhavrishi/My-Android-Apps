package uncc.com.hw3_groups25;



import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;

public class TextSearchUtil {
    private static ArrayList<String> fileLines;
    private static ArrayList<String> result;



    private static void wordSearch(ArrayList<String> fileLines, String keyWord){
        for(int i = 0; i < fileLines.size(); i++){
            String line = fileLines.get(i);
            String[] lineWords = line.split(" ");

            ArrayList<String> wordsList = new ArrayList<String>(Arrays.asList(lineWords));

            if(wordsList.indexOf(keyWord) > 0){

                int wordIndex = wordsList.indexOf(keyWord);
                int oldIndex = wordIndex;

                while(oldIndex >= 0){
                    int index = TextUtils.join(" ", Arrays.copyOfRange(lineWords, 0, wordIndex)).length();

                    oldIndex = (wordsList.subList(wordIndex + 1, wordsList.size())).indexOf(keyWord);

                    wordIndex = wordIndex + oldIndex + 1;
                    if(index != 0){
                        index = index + 1;
                    }

                    int beginIndex = index - 10;

                    String oldStartString = "";

                    boolean lastWord = false;
                    int lastStringBalance = 0;

                    if(i == fileLines.size() - 1){
                        int availableLength = line.length() - (index + keyWord.length());
                        int requiredLength = 20 - keyWord.length();
                        if(availableLength < requiredLength){
                            lastWord = true;
                            if(index >= requiredLength - availableLength + 10){
                                oldStartString = line.substring(index - requiredLength + availableLength - 10, index);
                            } else{
                                oldStartString = line.substring(0, index);
                                lastStringBalance = requiredLength - availableLength + 10 - oldStartString.length();
                            }
                        }

                    }

                    if(beginIndex < 0 || lastWord){
                        beginIndex = 0;
                        int currentIndex = i;
                        int balanceLength = 10 - index;

                        if(lastWord){
                            balanceLength = lastStringBalance;
                            beginIndex = index;
                        }

                        while(balanceLength > 0 && currentIndex - 1 >= 0){

                            String oldLine = fileLines.get(currentIndex - 1);

                            if(oldLine.length() < balanceLength){
                                oldStartString = oldLine + oldStartString;
                                balanceLength = balanceLength - oldLine.length();
                            } else{
                                oldStartString =  oldLine.substring(oldLine.length() - balanceLength) + oldStartString;
                                balanceLength = 0;
                            }

                            currentIndex = currentIndex - 1;
                        }
                    }

                    if(index == 0 && i != 0){
                        oldStartString = oldStartString.substring(1) + " ";
                    }

                    String startString = oldStartString + line.substring(beginIndex, index);

                    int endIndex = index - startString.length() + 30;

                    String endString;

                    if(endIndex >= line.length()){
                        endString = line.substring(index + keyWord.length());
                    } else{
                        endString = line.substring(index + keyWord.length(), endIndex);
                    }

                    if(endString.equals("") && !lastWord){
                        endString = " ";
                    }

                    String finalString = startString + line.substring(index, index + keyWord.length()) + endString;


                    int currentIndex = i;

                    while(finalString.length() < 30 && currentIndex + 1 < fileLines.size()){

                        String nextLine = fileLines.get(currentIndex + 1);

                        int balanceLength = 30 - finalString.length();

                        if(nextLine.length() < balanceLength){
                            finalString = finalString + nextLine;
                        } else{
                            finalString = finalString + nextLine.substring(0, balanceLength);
                        }

                        currentIndex = currentIndex + 1;
                    }

                    result.add(finalString);
                }
            }
        }
    }

    /**
     * @param filePath Path of the text file
     * @param keyWord Word to search for in the file
     *
     * @return ArrayList of search results with trailing and leading texts
     *
     */
    public static ArrayList<String> SearchKeyWordInFile(ArrayList<String> myfileLines, String filePath, String keyWord, int casesensitive) {
        fileLines = new ArrayList<>();
        result = new ArrayList<>();
        fileLines = myfileLines;

        if (casesensitive != 1) {
            ArrayList<String> comboString = new ArrayList<String>();
            comboString = PrintCombinations(keyWord);
            for (int i = 0; i < comboString.size(); i++){
                wordSearch(fileLines, comboString.get(i));
            }
        }else {
            wordSearch(fileLines, keyWord);
        }

        return result;

    }


    public static ArrayList<String> PrintCombinations(String input) {
        ArrayList<String> comboString = new ArrayList<String>();
        char[] currentCombo = input.toCharArray();
        BitSet bv = new BitSet(input.length());
        bv.set(0, currentCombo.length);

        while(!bv.isEmpty()) {
            for(int i = 0; i < currentCombo.length; ++i) {
                if(bv.get(i)) // If the bit is set
                    currentCombo[i] = Character.toUpperCase(currentCombo[i]);
                else
                    currentCombo[i] = Character.toLowerCase(currentCombo[i]);
            }

            comboString.add(String.valueOf(currentCombo));

            DecrementBitVector(bv, currentCombo.length);
        }

        comboString.add(input.toLowerCase());
        return comboString;
    }


    public static void DecrementBitVector(BitSet bv, int numberOfBits) {
        int currentBit = numberOfBits - 1;
        while(currentBit >= 0) {
            bv.flip(currentBit);

            if(!bv.get(currentBit))
                break;
            currentBit--;
        }
    }

}
