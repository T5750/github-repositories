package cn.ictgu.tools.tag;

/**
 * 带指针的字符串
 */
public class StringPointer implements CharSequence, Comparable<StringPointer> {

    private final char[] value;

    private final int offset;

    public final int length;

    public StringPointer(String str) {
        value = str.toCharArray();
        offset = 0;
        length = value.length;
    }

    private StringPointer(char[] value, int offset, int length) {
        this.value = value;
        this.offset = offset;
        this.length = length;
    }

    /**
     * 计算从该位置起2个字符的hash值
     *
     * @param i 从 0 到 length - 2
     * @return hash值
     */
    public int nextTwoCharHash(int i) {
        return 31 * value[offset + i] + value[offset + i + 1];
    }

    /**
     * 计算从该位置起2个字符的和<br/>
     * 和相同，即2个字符相同
     *
     * @param i 从 0 到 length - 2
     * @return int值
     */
    public int nextTwoCharMix(int i) {
        return (value[offset + i] << 16) | value[offset + i + 1];
    }

    /**
     * 判断该位置后的字符串，是否以某个词开头
     *
     * @param i    从 0 到 length - 2
     * @param word 词
     * @return 是否？
     */
    public boolean nextStartsWith(int i, StringPointer word) {
        // 是否长度超出
        if (word.length > length - i) {
            return false;
        }
        // 从尾开始判断
        for (int c = word.length - 1; c >= 0; c--) {
            if (value[offset + i + c] != word.value[word.offset + c]) {
                return false;
            }
        }
        return true;
    }

    public int length() {
        return length;
    }

    public char charAt(int i) {
        return value[offset + i];
    }

    public StringPointer substring(int begin) {
        return new StringPointer(value, offset + begin, length - begin);
    }

    private StringPointer substring(int begin, int end) {
        return new StringPointer(value, offset + begin, end - begin);
    }

    public CharSequence subSequence(int start, int end) {
        return substring(start, end);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(value[i]);
        }
        return sb.toString();
    }

    public int compareTo(StringPointer that) {
        int length = this.length;
        int thatLength = that.length;
        int lim = Math.min(length, thatLength);
        char values[] = this.value;
        char thatValues[] = that.value;

        int k = 0;
        while (k < lim) {
            char c1 = values[this.offset + k];
            char c2 = thatValues[that.offset + k];
            if (c1 != c2) {
                return c1 - c2;
            }
            k++;
        }
        return length - thatLength;
    }

}
