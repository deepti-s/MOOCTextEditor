package spelling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteMatchCase implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;


    public AutoCompleteMatchCase()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
		if (word != null && !isWord(word)) {
			TrieNode currentNode = root, nextNode = new TrieNode();
			for (Character ch : word.toLowerCase().toCharArray()) {
				nextNode = currentNode.insert(ch);
				if (nextNode == null) {
					nextNode = currentNode.getChild(ch);
				}
				currentNode = nextNode;
			}
			nextNode.setEndsWord(true);
			size++;
			return true;
		}
		return false;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
		if (s != null) {
			TrieNode currentNode = root, nextNode;
			int index = 0;
			while (index < s.length()) {
				nextNode = currentNode.getChild(Character.toLowerCase(s.charAt(index)));
				if (nextNode == null) {
					return false;
				} else if (s.length() == ++index && nextNode.endsWord()) {
					return true;
				}
				currentNode = nextNode;
			}
		}
		return false;
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to "numCompletions" best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
		 if (prefix == null) {
			 return null;
		 }
		 List<String> suggestedCompletions = new ArrayList<String>();
		 TrieNode stemNode = getStemNode(prefix);

		 if (stemNode != null) {
			 LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
			 queue.add(stemNode);

			 while(!queue.isEmpty() && suggestedCompletions.size() < numCompletions) {
				 TrieNode currentNode = queue.remove();
				 if(currentNode.endsWord()) {
					 suggestedCompletions.add(currentNode.getText());
				 }
				 queue.addAll(currentNode.getChildNodes());
			 }

		 }
         return suggestedCompletions;
     }

	private TrieNode getStemNode(String prefix) {
		TrieNode stemNode = root;
		if (!prefix.isEmpty()) {
			int index = 0;
			while (index < prefix.length() && stemNode != null) {
				stemNode = stemNode.getChild(Character.toLowerCase(prefix.charAt(index++)));
			}
		}
		return stemNode;

	}

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}