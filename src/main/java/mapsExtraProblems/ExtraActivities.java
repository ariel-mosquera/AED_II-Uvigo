package mapsExtraProblems;

/*-
 * #%L
 * AEDII - Activities
 * %%
 * Copyright (C) 2025 - 2026 Rosalía Laza Fidalgo, María Reyes Pavón Rial,
 * Florentino Fernández Riverola, María Novo Lourés, and Miguel Reboiro Jato
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import es.uvigo.esei.aed2.map.Map;
import es.uvigo.esei.aed2.map.HashMap;

import java.util.HashSet;
import java.util.Set;

public class ExtraActivities {

    /*
     * Ejercicio de moovi (codeRunner):
     * 
     * Escribe un método que reciba como entrada una cadena de texto y construya un
     * índice invertido donde cada palabra se asocie al conjunto de posiciones donde
     * aparece en el texto. Las palabras deben procesarse suponiendo que el texto
     * está en minúsculas y sin signos de puntuación.
     * 
     */

    // solution with the split() method
    public static Map<String, Set<Integer>> indexDocument(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new HashMap<>();
        }

        Map<String, Set<Integer>> wordsIndexes = new HashMap<>();
        int index = 0;
        String[] words = text.split(" ");

        for (String currWord : words) {
            if (!currWord.isEmpty()) {
                Set<Integer> setOfIndexes = wordsIndexes.get(currWord);

                if (setOfIndexes == null) {
                    setOfIndexes = new HashSet<>();
                    wordsIndexes.add(currWord, setOfIndexes);
                }

                setOfIndexes.add(index++);
            }
        }

        return wordsIndexes;
    }

    // Solution without the split method in case your tools were limitated
    static Map<String, Set<Integer>> indexDocumentAlternative(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new HashMap<>();
        }

        Map<String, Set<Integer>> mapOfIndexes = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        boolean inWord = false;
        int index = 0;

        for (int i = 0; i < text.length(); i++) {
            char current = text.charAt(i);

            if (current == ' ') {
                inWord = false;
            } else if (!inWord) {
                inWord = true;
            }

            if (inWord) {
                builder.append(current);
            }

            if ((!inWord || i == text.length() - 1) && builder.length() > 0) {
                String word = builder.toString();
                Set<Integer> setOfIndexes = mapOfIndexes.get(word);

                builder.delete(0, builder.length());

                if (setOfIndexes == null) {
                    setOfIndexes = new HashSet<>();
                    mapOfIndexes.add(word, setOfIndexes);
                }

                setOfIndexes.add(index++);
            }
        }

        return mapOfIndexes;
    }
}
