import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Escreva uma descrição da classe SpellChecker aqui.
 * 
 * @author (seu nome)
 * @version (um número da versão ou uma data)
 */
public class SpellChecker {
    private DictReader dicionario;

    // recebe o arquivo de texto com as palavras
    public SpellChecker(DictReader dicionario) {
        this.dicionario = dicionario;
    }
 
    public void save(){
        ArrayList<String> dicionariO = dicionario.getDictionary();
        dicionario.save(dicionariO);
    }

    // conta a quantidade de palavras
    public int numberOfWords() {
        int cont = 0;
        for (String word : dicionario.getDictionary()) {
            cont++;
        }
        return cont;
    }

    // verifica se a palvra solicitada existe no arquivo
    public boolean isKnownWord(String word) {
        boolean igual = false;
        for (String wordi : dicionario.getDictionary()) {
            if (wordi.equals(word)) {
                igual = true;
            }
        }
        return igual;
    }

    // verifica se todas as palavras passadas por um array estao no arquivo
    public boolean allKnown(ArrayList<String> words) {
        boolean igual = false;
        for (String wordi : words) {
            boolean ingual = false;
            for (String word : dicionario.getDictionary()) {
                if (wordi.equals(word)) {
                    ingual = true;
                }
            }
            if(ingual){
                igual = true;
            }else{
                igual = false;
            }
        }
        return igual;
    }

    
    // retorna palavras com os mesmos prefixos
    public ArrayList<String> wordsStartingWith(String prefix) {

        ArrayList<String> palavras = new ArrayList<String>();
        palavras.add("asdsad");
        for (String word : dicionario.getDictionary()) {
            String formatedWord = word.toLowerCase();
            String formatedPrefix = prefix.toLowerCase();

            if (formatedPrefix.startsWith(formatedWord)) {
                palavras.add(word);
                System.out.println(word);
            }
        }


        return palavras;
    }

    public void insert(String newWord) {
        if (isKnownWord(newWord) == false) {
            ArrayList<String> dici = dicionario.getDictionary();
            dici.add(newWord);
            Collections.sort(dici, String.CASE_INSENSITIVE_ORDER);
            dicionario.addDeletWord(dici);
        }
    }

    public boolean remove(String wordRemove) {

        boolean remove = false;
        if (isKnownWord(wordRemove)) {
            ArrayList<String> dici = dicionario.getDictionary();
            dici.remove(wordRemove);
            Collections.sort(dici, String.CASE_INSENSITIVE_ORDER);
            remove = (dicionario.addDeletWord(dici));
        }
        if (remove) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPalindrome(String word) {
        if (isKnownWord(word)) {
            String palindromeWord = "";
            for (int i = word.length() - 1; i >= 0; i--) {
                palindromeWord += word.charAt(i);
            }

            if (word.equals(palindromeWord)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public ArrayList<String> anagrams(String word) {

        ArrayList<String> anagramas = new ArrayList<>();

        String word1Formated = word.toLowerCase();
        char[] word1 = word1Formated.toCharArray();
        Arrays.sort(word1);

        for (String palavra : dicionario.getDictionary()) {
            
            String word2Formated = palavra.toLowerCase();
            char[] word2 = word2Formated.toCharArray();
            Arrays.sort(word2);

            if(Arrays.equals(word1,word2)){
                anagramas.add(palavra);
            }
        }

        return anagramas;
    }

  public ArrayList<String> difference(ArrayList<String> dictionary) {

        ArrayList<String> diferentes = new ArrayList<>();
        
        for (String palavra : dictionary) {
            boolean diferent = false;

            for (String palavra2 : dicionario.getDictionary()) {
                if (palavra.equals(palavra2) == false) {
                    diferent = true;
                } else {
                    diferent = false;
                    break;
                }
            }
            if (diferent) {
                diferentes.add(palavra);
            }
        }

        for (String palavra : dicionario.getDictionary()) {
            boolean diferent = false;

            for (String palavra2 : dictionary) {
                if (palavra.equals(palavra2) == false) {
                    diferent = true;
                } else {
                    diferent = false;
                    break;
                }
            }
            if (diferent) {
                diferentes.add(palavra);
            }
        }

        for(String pali:diferentes){
            System.out.println(pali);
        }
        return diferentes;
    }



    public int distance(String word1, String word2) {
        int distancia = 0;
        int tamanhoWord1 = word1.length();
        int tamanhoWord2 = word1.length();

        int[][] matriz = new int[tamanhoWord1 + 1][tamanhoWord2 + 1];

        for (int i = 0; i <= tamanhoWord1; i++) {
            matriz[i][0] = i;
        }

        for (int j = 0; j <= tamanhoWord2; j++) {
            matriz[0][j] = j;
        }

        for(int i = 1;i<=tamanhoWord1;i++){
            for(int j = 1;j<=tamanhoWord2;j++){
                int custo = (word1.charAt(i-1) == word2.charAt(j-1))?0:1;
                int minimo1 = Math.min(matriz[i-1][j]+1,matriz[i][j-1]+1);
                matriz[i][j] = Math.min(minimo1, matriz[i-1][j-1]+custo);
            }
            distancia = matriz[tamanhoWord1][tamanhoWord2];
        }
        return distancia;
    }

}
