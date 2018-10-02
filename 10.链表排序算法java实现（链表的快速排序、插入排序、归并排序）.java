难易程度：★★

重要性：★★★

链表的排序相对数组的排序更为复杂些，也是考察求职者是否真正理解了排序算法（而不是）

转载自微信公众号：“菜一个专注应届生互联网求职分享的公众号“菜鸟名企梦”

1.链表的插入排序

public  class LinkedInsertSort {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode insertionSortList(ListNode head) {
        if(head==null||head.next==null)    return head;

        ListNode pre = head;//pre指向已经有序的节点
        ListNode cur = head.next;//cur指向待排序的节点

        ListNode aux = new ListNode(-1);//辅助节点
        aux.next = head;

        while(cur!=null){
            if(cur.val<pre.val){
              //先把cur节点从当前链表中删除，然后再把cur节点插入到合适位置
                pre.next = cur.next;

                //从前往后找到l2.val>cur.val,然后把cur节点插入到l1和l2之间
                ListNode l1 = aux;
                ListNode l2 = aux.next;
                while(cur.val>l2.val){
                    l1 = l2;
                    l2 = l2.next;
                }
                //把cur节点插入到l1和l2之间
                l1.next = cur;
                cur.next = l2;//插入合适位置

                cur = pre.next;//指向下一个待处理节点

            }else{
                pre = cur;
                cur = cur.next;
            }
        }
        return aux.next;
    }
}

2.链表的快速排序

public static ListNode quickSort(ListNode begin, ListNode end) {
        //判断为空，判断是不是只有一个节点
        if (begin == null || end == null || begin == end)
            return begin;
        //从第一个节点和第一个节点的后面一个几点
        //begin指向的是当前遍历到的最后一个<= nMidValue的节点
        ListNode first = begin;
        ListNode second = begin.next;

        int nMidValue = begin.val;
        //结束条件，second到最后了
        while (second != end.next && second != null) {//结束条件
          //一直往后寻找<=nMidValue的节点，然后与fir的后继节点交换
            if (second.val < nMidValue) {
                first = first.next;
                //判断一下，避免后面的数比第一个数小，不用换的局面
                if (first != second) {
                    int temp = first.val;
                    first.val = second.val;
                    second.val = temp;
                }
            }
            second = second.next;
        }
        //判断，有些情况是不用换的，提升性能
        if (begin != first) {
            int temp = begin.val;
            begin.val = first.val;
            first.val = temp;
        }
        //前部分递归
        quickSort(begin, first);
        //后部分递归
        quickSort(first.next, end);
        return begin;
    }
    
3.链表的归并排序

public ListNode mergeSort(ListNode head){
        if(head==null || head.next==null)    return head;

        ListNode mid = getMid(head);//获取链表中间节点

        //把链表从之间拆分为两个链表：head和second两个子链表
        ListNode second = mid.next;
        mid.next = null;

        //对两个子链表排序
        ListNode left = mergeSort(head);
        ListNode right = mergeSort(second);

        return merge(right,left);      
    }

    //两个有序链表的归并
    private ListNode merge(ListNode l1,ListNode l2){
      //辅助节点，排好序的节点将会链接到dummy后面
        ListNode dummy = new ListNode(0);

        ListNode tail = dummy;//tail指向最后一个排好序的节点
        while(l1!=null&&l2!=null){
            if(l1.val<=l2.val){
                tail.next = l1;
                l1 = l1.next;
            }else{
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next; //移动tail指针        
        }

        if(l1!=null)
            tail.next = l1;
        else
            tail.next = l2;

        return dummy.next;

    }

    //返回链表之间节点
    private ListNode getMid(ListNode head){
        if(head==null ||head.next==null)    return head;

        ListNode slow = head;
        ListNode faster = head.next;
        while(faster!=null&&faster.next!=null){
            slow = slow.next;
            faster = faster.next.next;
        }
        return slow;
    }     

     
