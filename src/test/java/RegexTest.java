import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegexTest {
    Regex regex;

    @Before
    public void setup() {
        regex = new Regex();
    }

    @Test
    public void givenSingleLetter_whenTextSameSingleLetter_thenTrue() {
        assertTrue(regex.search("a", "a"));
        assertTrue(regex.search(".", "z"));
        assertTrue(regex.search("", "h"));
    }

    @Test
    public void givenPattern_withSingleCharAndWrongText_thenFalse() {
        assertFalse(regex.search("a", "b"));
        assertFalse(regex.search("a", ""));
    }

    @Test
    public void givenPatternWithDot_WhenText_thenTrue() {
        assertTrue(regex.search("a.c", "avc"));
        assertTrue(regex.search(".c", "ac"));
        assertTrue(regex.search(".c", "bc"));
    }

    @Test
    public void givenPattern_withStartWithChar_thenTrue() {
        assertTrue(regex.search("^abc", "abc"));
        assertTrue(regex.search("^abcd", "abcd"));
        assertTrue(regex.search("bc", "abcd"));
    }

    @Test
    public void givenPattern_withQuestionChar_thenTrue() {
        assertTrue(regex.search("ab?c", "ac"));
        assertTrue(regex.search("ab?c", "abc"));
        assertTrue(regex.search("a?b?c?", "abc"));
        assertTrue(regex.search("a?b?c?", ""));
    }

    @Test
    public void givenPattern_withStarChar_thenTrue() {
        assertTrue(regex.search("a*", ""));
        assertTrue(regex.search("a*", "aaaaaaa"));
        assertTrue(regex.search("a*b", "aaaaaaab"));
    }

    // match one
    @Test
    public void givenSingleCharPatter_matchWithText_thenTrue() {
        assertTrue(regex.search("a", "a"));
        assertTrue(regex.search("", ""));

        assertFalse(regex.search("b", ""));
        assertFalse(regex.search("a", "c"));

        assertTrue(regex.search(".", "c"));
        assertTrue(regex.search(".", "q"));
    }

    @Test
    public void match() {
        assertTrue(regex.search("", "abc"));
        assertTrue(regex.search("", "cab"));
        assertTrue(regex.search("$", ""));

        assertFalse(regex.search("$", "abc"));

        assertTrue(regex.search("abc", "abc"));
        assertTrue(regex.search("bac", "bac"));
        assertTrue(regex.search("a.c", "abc"));
        assertTrue(regex.search("b.c", "bac"));
    }

    @Test
    public void test() {
        assertTrue(regex.search("^please work", "please work"));
        assertTrue(regex.search("^a good t.st", "a good test"));
        assertTrue(regex.search("^an.ther g..d test", "another good test"));

        assertFalse(regex.search("^b.d test", "baad test"));
        assertTrue(regex.search("^match end$", "match end"));

        assertFalse(regex.search("^match$", "match end"));
        assertTrue(regex.search("^partial", "partial match"));
        assertTrue(regex.search("^good", "good test"));

        assertFalse(regex.search("^bad", "ba test"));

        assertTrue(regex.search("match", "this is a match"));
        assertTrue(regex.search("what", "this is what we are doing"));
        assertTrue(regex.search("is what", "this is what we are doing"));

        assertFalse(regex.search("blah", "this is what we are doing"));
        assertFalse(regex.search("iswhat", "this is what we are doing"));
        assertFalse(regex.search("pattern", ""));
        assertTrue(regex.search("", ""));
        assertTrue(regex.search("$", ""));

        assertTrue(regex.search("a?", ""));
        assertTrue(regex.search("a?", "b"));

        assertTrue(regex.search("thi?s", "ths"));
        assertTrue(regex.search("this is?", "this i"));

        assertFalse(regex.search("this is? it", "this i"));

        assertTrue(regex.search("a?", "a"));
        assertTrue(regex.search("b?", "b"));

        assertTrue(regex.search("one?", "one"));
        assertTrue(regex.search("one? of us", "one of us"));

        assertFalse(regex.search("is it one? of us", "is it one"));

        assertTrue(regex.search("is? it? r?e?a?lly", "is it really"));
        assertTrue(regex.search("is? it? r?e?a?lly", "i i lly"));

        assertFalse(regex.search("is? it? r?e?a?lly", "i i ly"));

        assertTrue(regex.search("a*", ""));
        assertTrue(regex.search("a*", "b"));
        assertTrue(regex.search("b*", "aaaaa"));

        assertTrue(regex.search("a*", "a"));
        assertTrue(regex.search("a*", "aaaaa"));
        assertTrue(regex.search("a*a", "aaaaa"));

        assertTrue(regex.search("this* i*s the str*ing", "thissss s the strrring"));
        assertFalse(regex.search("this* i*s the str*ing", "thissss i the strrrng"));
        assertFalse(regex.search("this* i*s the str*ing", "thissss i the srrrng"));

    }
}
