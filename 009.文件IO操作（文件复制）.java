难易程度：★★

重要性：★★★


网易的面试中层要求手写实现文件的复制；java.io包下类数量庞大，求职者应该掌握基本的IO文件操作，在求职面试过程中，文件复制出镜率较高。

一个专注应届生互联网求职分享的公众号“菜鸟名企梦”
// 通过字节流实现文件的复制
    private void readStream(String input, String outPut) throws Exception {
        try (
                FileInputStream fis = new FileInputStream(input);
                BufferedInputStream bis = new BufferedInputStream(fis);

                FileOutputStream fos = new FileOutputStream(outPut);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
             ) {

            byte[] b = new byte[1024];
            int len = 0;
            while ((len = bis.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            bos.flush();

        }
    }
//通过字符流实现文件的复制
    private void read(String input, String output) throws IOException {
        try (
                FileInputStream fis = new FileInputStream(input); 
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);

                FileOutputStream fos = new FileOutputStream(output);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                PrintWriter pw = new PrintWriter(osw)) {

            char[] buf = new char[1024];
            int len = 0;
            while ((len = br.read(buf)) != -1) {
                pw.write(buf, 0, len);
            }
            pw.flush();
        }
    }
