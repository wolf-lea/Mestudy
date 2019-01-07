package com.tecsun.sisp.adapter.common.util.qrcode;

import com.google.zxing.common.BitMatrix;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by Administrator on 2018/9/12.
 */
public class MatrixToImageWriter {
    private static final int BLACK = 0xFF000000;//用于设置图案的颜色
    private static final int WHITE = 0xFFFFFFFF; //用于背景色

    private MatrixToImageWriter() {
    }

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y,  (matrix.get(x, y) ? BLACK : WHITE));
//              image.setRGB(x, y,  (matrix.get(x, y) ? Color.YELLOW.getRGB() : Color.CYAN.getRGB()));
            }
        }
        return image;
    }

    public static String writeToFile(BitMatrix matrix, String format, File file,String logoUri) throws IOException {

        System.out.println("write to file");
        BufferedImage image = toBufferedImage(matrix);
        //设置logo图标
        QRCodeFactory logoConfig = new QRCodeFactory();
        
        /* 如果需要在二维码中设置logo，放开下面的注释即可 */
        //image = logoConfig.setMatrixLogo(image, logoUri);
        
        String qrBase64 = "";
        if (!ImageIO.write(image, format, file)) {
            System.out.println("生成图片失败");
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }else{
            //将图片转换为base64
            InputStream in = null;
            byte[] data = null;
            try {
                in = new FileInputStream(file.getPath());
                data = new byte[in.available()];
                in.read(data);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 对字节数组Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
             qrBase64 = encoder.encode(data);
            System.out.println("图片生成成功！");
        }
        return  qrBase64;
    }

    public static void writeToStream(BitMatrix matrix, String format, OutputStream stream,String logoUri) throws IOException {

        BufferedImage image = toBufferedImage(matrix);

        //设置logo图标
        QRCodeFactory logoConfig = new QRCodeFactory();
        
        /* 如果需要在二维码中设置logo，放开下面的注释即可  */
        //image = logoConfig.setMatrixLogo(image, logoUri);

        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }

}
