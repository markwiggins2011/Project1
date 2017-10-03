package edu.gcccd.csis.p1;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class SequenceSearchImpl extends SequenceSearch {
	private ArrayList<String> sequenceList;
	private String longestTaggedSequence;

    public SequenceSearchImpl(final String content, final String start, final String end) {
        super(content, start, end);
        sequenceList = new ArrayList<>();
        longestTaggedSequence = "";
        int startSize = startTag.length();
        int endSize = endTag.length();
        String tempCont = content;
        while (tempCont.indexOf(startTag) != -1) {
            int sta = tempCont.indexOf(startTag) + startSize;
            int endPos = tempCont.indexOf(endTag,sta);
            String taggedSequence = tempCont.substring(sta, endPos);
            sequenceList.add(taggedSequence);
            if (taggedSequence.length() >= longestTaggedSequence.length()) {
                longestTaggedSequence = taggedSequence;
            }
            tempCont = tempCont.substring(endPos + endSize);
        }
        if (sequenceList.size() == 0)
            longestTaggedSequence = null;
    }

    @Override
    public String[] getAllTaggedSequences() {
        String[] a = new String[sequenceList.size()];
        return sequenceList.toArray(a);
    }

    @Override
    public String getLongestTaggedSequence() { return longestTaggedSequence; }

    @Override
    public String displayStringArray() {
     StringBuilder sb = new StringBuilder();
	for (String sequence : sequenceList) {
	sb.append(sequence).append(" : ").append(sequence.length()).append("\n");
	}
	return sb.toString();
    }

    @Override
    public String toString() {
		return content.replaceAll(startTag,"").replaceAll(endTag,"");
    }
}