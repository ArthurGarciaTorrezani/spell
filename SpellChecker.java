import java.util.ArrayList;

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
            for (String word : dicionario.getDictionary()) {
                if (wordi.equals(word)) {
                    igual = true;
                } else {
                    igual = false;
                    break;
                }
            }
        }
        return igual;
    }

    // retorna palavras com os mesmos prefixos
    public ArrayList<String> wordsStartingWith(String prefix) {

        ArrayList<String> palavras = new ArrayList<String>();
        for (String word : dicionario.getDictionary()) {
            String formatedWord = word.toLowerCase();
            String formatedPrefix = prefix.toLowerCase();

            String result = "";
            for (int i = 0; i < prefix.length(); i++) {
                result += formatedWord.charAt(i);
            }
            if (result.equals(formatedPrefix)) {
                palavras.add(word);
            }
        }
        return palavras;
    }

    public void insert(String newWord) {
        boolean existe = false;
        for (String word : dicionario.getDictionary()) {
            String wordFormated = word.toLowerCase();
            if (wordFormated.equals(newWord)) {
                existe = true;
                break;
            }
        }
        if (existe == false) {
            ArrayList<String> dici = dicionario.getDictionary();
            dici.add(newWord);
            dicionario.addDeletWord(dici);
        }
    }

    public boolean remove(String wordRemove) {
        boolean existe = false;
        boolean ok = true;
        for (String word : dicionario.getDictionary()) {
            String wordFormated = word.toLowerCase();
            if (wordFormated.equals(wordRemove)) {
                existe = true;
                break;
            }
        }
        if (existe) {
            ArrayList<String> dici = dicionario.getDictionary();
            dici.remove(wordRemove);
            ok = (dicionario.addDeletWord(dici));
        }

        if (ok) {
            return true;
        } else {
            return false;
        }

    }
}
