难易程度：★★★
重要性：★★★★

在猿辅导的面试中曾出现过

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        
        //只有一个数组时直接计算中位数
        if (len2 == 0)
            return 0.0;
        if (len1 == 0) {
            if (len2 % 2 != 0)
                return nums2[len2 / 2] * 1.0;
            else {
                return (nums2[len2 / 2 - 1] + nums2[len2 / 2]) / 2.0;
            }

        }
        
        /*
         * 保证 len1<=len2
         */
        if (len1 > len2) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
            len1 = nums1.length;
            len2 = nums2.length;
        }

/*
 * 设nums1的长度为len1；nums2的长度为len2：
 * i将长度的len1的数组一份为二，i的取值可以为0~len1，共len1+1种取值；每个i取值都将nums1数组一分为二，划分后的两个子数组的长度为：i和len1-i
 * j将长度为len2的数组一份为二，j的取值可以为0~len2，共len2+1种取值，每个j取值都将nums2数组一分为二，互粉后的两个子数组的长度为：j和len2-j
 * 
 * 中位数将数组整体一份为二，并且具有如下特点：中位数将合并后的有序数组划分为两个等长或者长度相差为1的两部分：
 * 如图：
 *             i 
 *  nums1[i-1] / nums1[i]
 * 
 *  nums2[j-1] / nums2[j]
 *            j
 * 
 * 上图的两个'/'分别代表i和j的切分位置，即i和j的取值;对于中位数而言，我们只需保证： 
 * nums1[i-1]<=nums2[j]
 * nums2[j-1]<=nums1[i]
 * 
 * 并且：当len1+len2为偶数时，左右两边数组长度相等：i+j = (len1-i)+(len2-j) -> i+j = (len1+len2)/2
 * 当len1+len2为奇数时，假设将多出的那个数划分到左侧,即左侧比右侧长度大一：i+j = (len1-i)+（len2-j)+1 -> i+j=(len1+n+1)/2
 * 
 * 综上，只需要满足以下三个条件：
 *  nums1[i-1]<=nums2[j]
 *  nums2[j-1]<=nums1[i]
 *  i+j = (len1+n+1)/2
 * 
 * 如果我们找到了满足上面三个条件的i和j：
 *  len1+len2为偶数时：中位数 =（Math.max(nums1[i-1],nums2[j-1])+Math.min(nums1[i],nums2[j])/2.0)
 * 如果len1+len2为奇数时：中位数 = Math.max(nums1[i-1],nums2[j-1])  因为左边比右边多一个元素，左边多出的那个元素就是中位数
 * 
 * 所以，我们只要搜索一个i的取值，同时有j = (len1+len2+1)/2 - i，使得满足下面两个条件：
 * nums1[i-1]<=nums2[j] 
 * nums2[j-1]<=nums1[i]
 * 
 * 对于i的取值，我们可以使用二分搜索，所以这个问题在O(log(len1))复杂度就可以解决；另外需要注意i和j取值边界问题：
 * 因为i的取值是在[0,len1]范围进行搜索，所以索引i不会有越界问题；但是对于j就有可能出现索引越界的可能：
 * 对此只需要保证：len1<=len2就可避免索引j的越界问题，证明如下：
 * 
 *      len1<=len2 和 j=(len1+len2+1)/2-i（上面推导过整个表达式），i在[0,len1]区间
 *  ->
 *      j>=(len1+n+1)/2-len1=(len2-len1+1)/2,    ->    j>=0; 
 *      j<=(len1+n+1)/2       ->     j<=len2
 * 综上：当len1<=len2时：0<=j<=n，即当满足len1<=len2时，索引j的取值一定在[0,len2]
 * 
 * 分析总结：
 * 我们的目标是找到一个i、 j=(len1+len2+1)/2-i，使得切分后两个数组左边子数组长度之和和右边子数组长度之和相等或者相差1，只要满足：
 * len1<=len2
 * j=(len1+len2+1)/2-i
 * nums1[i-1]<=nums2[j]
 * nums2[j-1]<=nums1[i]
 */

        int i_min = 0;
        int i_max = len1; // i取值在[0,length1]
        int aux = (len1 + len2 + 1) / 2;// j = (len1+len2+1)/2 -
                                        // i,前半部分取值，所以:j=aux-i

        while (i_min <= i_max) {
            int i = (i_min + i_max) / 2;// 二分搜索i
            int j = aux - i;
            if (i > 0 && nums1[i - 1] > nums2[j])// i>0即有j<(len2+len2+1)/2=len2,
                i_max = i - 1;
            // 想办法减小nums1[i-1],因为nums1是升序，所以减小nums1[i-1]就是减小i，即i取值缩小在前半部分区间
            else if (i < len1 && nums1[i] < nums2[j - 1])// i<len1，j>(len1+n+1)/2-len1=(len2-len1+1)/2>=0
                                                         // -> j>0,所以j-1索引有效
                i_min = i + 1;
            // 想办法增大nums1[i],因为nums1是升序，所以增大nums1[i]就是增大i，即i取值只缩小在后半部分区间
            else {
                // 找到了满足条件的i和j了

                // 由之前知道，如果总的数组长度为奇数，左边部分比右边部分长度多一个，当然最后的中位数也就是左边最大值了

                // 当length1+length2为奇数的时候，中位数就是左半部分数组的最大值，max_left；
                // 若数组总长度是偶数，中位数为：(max_left+max_right)/2
                // 所以至少需要先找出max_left；至于max_right与总长度是奇数还是偶数有关

                // 求解max_left
                int max_left = 0;
                if (i == 0)// 处理边界
                    max_left = nums2[j - 1];
                else if (j == 0)// 处理边界
                    max_left = nums1[i - 1];
                else
                    max_left = Math.max(nums1[i - 1], nums2[j - 1]);

                if (((len1 + len2) & 1) != 0) {// 总长度为奇数，中位数为max_left
                    return max_left * 1.0;
                }

                // 总长度为偶数时，中位数为：(max_left+max_right)/2

                // 求解max_right
                int min_right = 0;
                if (i == len1)// 处理边界
                    min_right = nums2[j];
                else if (j == len2)// 处理边界
                    min_right = nums1[i];
                else
                    min_right = Math.min(nums1[i], nums2[j]);

                return (max_left + min_right) / 2.0;

            }

        }
        return 1.0;// 只是为了通过编译

    }
}
