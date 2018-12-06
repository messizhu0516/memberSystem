package com.zhuqifeng.commons.utils.image;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.color.ColorSpace;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片压缩工具类 提供的方法中可以设定生成的 缩略图片的大小尺寸、压缩尺寸的比例、图片的质量等
 * 
 * <pre>
 * 	调用示例：
 * resiz(srcImg, tarDir + "car_1_maxLength_11-220px-hui.jpg", 220, 0.7F);
 * </pre>
 */
public class ImageUtil {
	
public static final float DEFAULT_QUALITY = 0.2125f ;
    
    
    /**
     * 
     * 添加图片水印操作(物理存盘,使用默认格式)
     * 
     * @param imgPath
     *            待处理图片
     * @param markPath
     *            水印图片
     * @param x
     *            水印位于图片左上角的 x 坐标值
     * @param y
     *            水印位于图片左上角的 y 坐标值
     * @param alpha
     *            水印透明度 0.1f ~ 1.0f
     * @param destPath
     *                 文件存放路径  
     * @throws Exception          
     * 
     */
     public static void addWaterMark(String imgPath, String markPath, int x, int y, float alpha,String destPath) throws Exception{
         try {
                BufferedImage bufferedImage = addWaterMark(imgPath, markPath, x, y, alpha);
                ImageIO.write(bufferedImage, imageFormat(imgPath), new File(destPath));
            } catch (Exception e) {
                throw new RuntimeException("添加图片水印异常");
            }
     }
    
        
    /**
     * 
     * 添加图片水印操作(物理存盘,自定义格式)
     * 
     * @param imgPath
     *            待处理图片
     * @param markPath
     *            水印图片
     * @param x
     *            水印位于图片左上角的 x 坐标值
     * @param y
     *            水印位于图片左上角的 y 坐标值
     * @param alpha
     *            水印透明度 0.1f ~ 1.0f
     * @param format
     *                 添加水印后存储的格式
     * @param destPath
     *                 文件存放路径  
     * @throws Exception          
     * 
     */
     public static void addWaterMark(String imgPath, String markPath, int x, int y, float alpha,String format,String destPath) throws Exception{
         try {
                BufferedImage bufferedImage = addWaterMark(imgPath, markPath, x, y, alpha);
                ImageIO.write(bufferedImage,format , new File(destPath));
            } catch (Exception e) {
                throw new RuntimeException("添加图片水印异常");
            }
     }
    
     
    /**
     * 
     * 添加图片水印操作,返回BufferedImage对象
     * 
     * @param imgPath
     *            待处理图片
     * @param markPath
     *            水印图片
     * @param x
     *            水印位于图片左上角的 x 坐标值
     * @param y
     *            水印位于图片左上角的 y 坐标值
     * @param alpha
     *            水印透明度 0.1f ~ 1.0f
     * @return
     *                 处理后的图片对象
     * @throws Exception          
     * 
     */
    public static BufferedImage addWaterMark(String imgPath, String markPath, int x, int y, float alpha) throws Exception{
        BufferedImage targetImage = null;
        try {
                        // 加载待处理图片文件
            Image img = ImageIO.read(new File(imgPath));

                        //创建目标图象文件
            targetImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = targetImage.createGraphics();
            g.drawImage(img, 0, 0, null);
            
                        // 加载水印图片文件
            Image markImg = ImageIO.read(new File(markPath));
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            g.drawImage(markImg, x, y, null);
            g.dispose();
        } catch (Exception e) {
            throw new RuntimeException("添加图片水印操作异常");
        }
        return targetImage;
       
    }

    /**
     * 
     * 添加文字水印操作(物理存盘,使用默认格式)
     * 
     * @param imgPath
     *            待处理图片
     * @param text
     *            水印文字    
     * @param font
     *            水印字体信息    不写默认值为宋体
     * @param color
     *            水印字体颜色
     * @param x
     *            水印位于图片左上角的 x 坐标值
     * @param y
     *            水印位于图片左上角的 y 坐标值
     * @param alpha
     *            水印透明度 0.1f ~ 1.0f
     * @param format
     *                 添加水印后存储的格式
     * @param destPath
     *                 文件存放路径     
     * @throws Exception          
     */
    public static void addTextMark(String imgPath, String text, Font font, Color color, float x, float y, float alpha,String destPath) throws Exception{
        try {
            BufferedImage bufferedImage = addTextMark(imgPath, text, font, color, x, y, alpha);
            ImageIO.write(bufferedImage, imageFormat(imgPath), new File(destPath));
        } catch (Exception e) {
            throw new RuntimeException("图片添加文字水印异常");
        }
    }
    
    /**
     * 
     * 添加文字水印操作(物理存盘,自定义格式)
     * 
     * @param imgPath
     *            待处理图片
     * @param text
     *            水印文字    
     * @param font
     *            水印字体信息    不写默认值为宋体
     * @param color
     *            水印字体颜色
     * @param x
     *            水印位于图片左上角的 x 坐标值
     * @param y
     *            水印位于图片左上角的 y 坐标值
     * @param alpha
     *            水印透明度 0.1f ~ 1.0f
     * @param format
     *                 添加水印后存储的格式
     * @param destPath
     *                 文件存放路径     
     * @throws Exception          
     */
    public static void addTextMark(String imgPath, String text, Font font, Color color, float x, float y, float alpha,String format,String destPath) throws Exception{
        try {
            BufferedImage bufferedImage = addTextMark(imgPath, text, font, color, x, y, alpha);
            ImageIO.write(bufferedImage, format, new File(destPath));
        } catch (Exception e) {
            throw new RuntimeException("图片添加文字水印异常");
        }
    }
    
    /**
     * 
     * 添加文字水印操作,返回BufferedImage对象
     * 
     * @param imgPath
     *            待处理图片
     * @param text
     *            水印文字    
     * @param font
     *            水印字体信息    不写默认值为宋体
     * @param color
     *            水印字体颜色
     * @param x
     *            水印位于图片左上角的 x 坐标值
     * @param y
     *            水印位于图片左上角的 y 坐标值
     * @param alpha
     *            水印透明度 0.1f ~ 1.0f
     * @return
     *                 处理后的图片对象
     * @throws Exception          
     */

    public static BufferedImage addTextMark(String imgPath, String text, Font font, Color color, float x, float y, float alpha) throws Exception{
        BufferedImage targetImage = null;
        try {
            Font Dfont = (font == null) ? new Font("宋体", 20, 13) : font;    
            Image img = ImageIO.read(new File(imgPath));
                        //创建目标图像文件
            targetImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = targetImage.createGraphics();
            g.drawImage(img, 0, 0, null);
            g.setColor(color);
            g.setFont(Dfont);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            g.drawString(text, x, y);
            g.dispose();
        } catch (Exception e) {
            throw new RuntimeException("添加文字水印操作异常");
        }
        return targetImage;
    }
    
    
    
    /**
     * 
     * 
     * 
     * 压缩图片操作(文件物理存盘,使用默认格式)
     * 
     * @param imgPath
     *                 待处理图片
     * @param quality
     *                 图片质量(0-1之間的float值)
     * @param width
     *                 输出图片的宽度    输入负数参数表示用原来图片宽
     * @param height
     *                 输出图片的高度    输入负数参数表示用原来图片高
     * @param autoSize
     *                 是否等比缩放 true表示进行等比缩放 false表示不进行等比缩放
     * @param format
     *                 压缩后存储的格式
     * @param destPath
     *                 文件存放路径
     * 
     * @throws Exception
     */
    public static void compressImage(String imgPath,float quality,int width, int height, boolean autoSize,String destPath)throws Exception{
        try {
            BufferedImage bufferedImage = compressImage(imgPath, quality, width, height, autoSize);
            ImageIO.write(bufferedImage, imageFormat(imgPath), new File(destPath));
        } catch (Exception e) {
            throw new RuntimeException("图片压缩异常");
        }
        
    }
    
    
    /**
     * 
     * 压缩图片操作(文件物理存盘,可自定义格式)
     * 
     * @param imgPath
     *                 待处理图片
     * @param quality
     *                 图片质量(0-1之間的float值)
     * @param width
     *                 输出图片的宽度    输入负数参数表示用原来图片宽
     * @param height
     *                 输出图片的高度    输入负数参数表示用原来图片高
     * @param autoSize
     *                 是否等比缩放 true表示进行等比缩放 false表示不进行等比缩放
     * @param format
     *                 压缩后存储的格式
     * @param destPath
     *                 文件存放路径
     * 
     * @throws Exception
     */
    public static void compressImage(String imgPath,float quality,int width, int height, boolean autoSize,String format,String destPath)throws Exception{
        try {
            BufferedImage bufferedImage = compressImage(imgPath, quality, width, height, autoSize);
            ImageIO.write(bufferedImage, format, new File(destPath));
        } catch (Exception e) {
            throw new RuntimeException("图片压缩异常");
        }
    }
    
    
    /**
     * 
     * 压缩图片操作,返回BufferedImage对象
     * 
     * @param imgPath
     *                 待处理图片
     * @param quality
     *                 图片质量(0-1之間的float值)
     * @param width
     *                 输出图片的宽度    输入负数参数表示用原来图片宽
     * @param height
     *                 输出图片的高度    输入负数参数表示用原来图片高
     * @param autoSize
     *                 是否等比缩放 true表示进行等比缩放 false表示不进行等比缩放
     * @return
     *                 处理后的图片对象
     * @throws Exception
     */
    public static BufferedImage compressImage(String imgPath,float quality,int width, int height, boolean autoSize)throws Exception{
        BufferedImage targetImage = null;
        if(quality<0F||quality>1F){
            quality = DEFAULT_QUALITY;
        }
        try {
            Image img = ImageIO.read(new File(imgPath));
                        //如果用户输入的图片参数合法则按用户定义的复制,负值参数表示执行默认值
            int newwidth =( width > 0 ) ? width : img.getWidth(null);
                        //如果用户输入的图片参数合法则按用户定义的复制,负值参数表示执行默认值
            int newheight = ( height > 0 )? height: img.getHeight(null);    
                        //如果是自适应大小则进行比例缩放
            if(autoSize){                                                    
                        // 为等比缩放计算输出的图片宽度及高度
                double Widthrate = ((double) img.getWidth(null)) / (double) width + 0.1;
                double heightrate = ((double) img.getHeight(null))/ (double) height + 0.1;
                double rate = Widthrate > heightrate ? Widthrate : heightrate;
                newwidth = (int) (((double) img.getWidth(null)) / rate);
                newheight = (int) (((double) img.getHeight(null)) / rate);
            }
                        //创建目标图像文件
            targetImage = new BufferedImage(newwidth,newheight,BufferedImage.TYPE_INT_RGB);
            Graphics2D g = targetImage.createGraphics();
            g.drawImage(img, 0, 0, newwidth, newheight, null);
                        //如果添加水印或者文字则继续下面操作,不添加的话直接返回目标文件----------------------
            g.dispose();
            
        } catch (Exception e) {
            throw new RuntimeException("图片压缩操作异常");
        }
        return targetImage;
    }
    
    
  
    /**
     * 图片黑白化操作(文件物理存盘,使用默认格式)
     * 
     * @param bufferedImage
     *                 处理的图片对象
     * @param destPath
     *                 目标文件地址
     * @throws Exception  
     *
     */
    public static void imageGray(String imgPath, String destPath)throws Exception{
        imageGray(imgPath, imageFormat(imgPath), destPath);
    }
    
    
    /**
     * 图片黑白化操作(文件物理存盘,可自定义格式)
     * 
     * @param bufferedImage
     *                 处理的图片对象
     * @param format
     *                 图片格式
     * @param destPath
     *                 目标文件地址
     * @throws Exception 
     * 
     */
    public static void imageGray(String imgPath,String format, String destPath)throws Exception{
        try {
             BufferedImage bufferedImage = ImageIO.read(new File(imgPath));
             ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);            
             ColorConvertOp op = new ColorConvertOp(cs, null);  
             bufferedImage = op.filter(bufferedImage, null);
             ImageIO.write(bufferedImage, format , new File(destPath));
        } catch (Exception e) {
            throw new RuntimeException("图片灰白化异常");
        }
    }
    
    
    
    /**
     * 图片透明化操作(文件物理存盘,使用默认格式)
     * 
     * @param imgPath
     *                 图片路径
     * @param destPath
     *                 图片存放路径
     * @throws Exception
     */
    public static void imageLucency(String imgPath,String destPath)throws Exception{
        try {
            BufferedImage bufferedImage = imageLucency(imgPath);
            ImageIO.write(bufferedImage, imageFormat(imgPath), new File(destPath));
        } catch (Exception e) {
            throw new RuntimeException("图片透明化异常");
        }
    }
    
    
    /**
     * 图片透明化操作(文件物理存盘,可自定义格式)
     * 
     * @param imgPath
     *                 图片路径
     * @param format
     *                 图片格式
     * @param destPath
     *                 图片存放路径
     * @throws Exception
     */
    public static void imageLucency(String imgPath,String format,String destPath)throws Exception{
        try {
            BufferedImage bufferedImage = imageLucency(imgPath);
            ImageIO.write(bufferedImage, format, new File(destPath));
        } catch (Exception e) {
            throw new RuntimeException("图片透明化异常");
        }
    }
    
    /**
     * 图片透明化操作返回BufferedImage对象
     * 
     * @param imgPath
     *                 图片路径
     * @return
     *                 透明化后的图片对象
     * @throws Exception 
     */
    public static BufferedImage imageLucency(String imgPath)throws Exception{
        BufferedImage targetImage = null;
        try {
                        //读取图片   
            BufferedImage img = ImageIO.read(new FileInputStream(imgPath));
                        //透明度
            int alpha = 0;    
                        //执行透明化
            executeRGB(img, alpha);
            targetImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = targetImage.createGraphics();
            g.drawImage(img, 0, 0, null);
            g.dispose();
        } catch (Exception e) {
            throw new RuntimeException("图片透明化执行异常");
        }
        return targetImage;
    }
    
    /**
     * 执行透明化的核心算法
     * 
     * @param img
     *                 图片对象
     * @param alpha
     *                 透明度
     * @throws Exception 
     */
    public static  void executeRGB(BufferedImage img, int alpha) throws Exception{
        int rgb = 0;//RGB值
                    //x表示BufferedImage的x坐标，y表示BufferedImage的y坐标
        for(int x=img.getMinX();x<img.getWidth();x++){
            for(int y=img.getMinY();y<img.getHeight();y++){
                     //获取点位的RGB值进行比较重新设定
                rgb = img.getRGB(x, y); 
                int R =(rgb & 0xff0000 ) >> 16 ; 
                int G= (rgb & 0xff00 ) >> 8 ; 
                int B= (rgb & 0xff ); 
                if(((255-R)<30) && ((255-G)<30) && ((255-B)<30)){ 
                    rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff); 
                    img.setRGB(x, y, rgb);
                }
            }
        }
    }
    
    
    /**
     * 图片格式转化操作(文件物理存盘)
     * 
     * @param imgPath    
     *                     原始图片存放地址
     * @param format
     *                     待转换的格式 jpeg,gif,png,bmp等
     * @param destPath
     *                     目标文件地址
     * @throws Exception
     */
    public static void formatConvert(String imgPath, String format, String destPath)throws Exception{
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(imgPath));
            ImageIO.write(bufferedImage, format, new File(destPath));
        } catch (IOException e) {
            throw new RuntimeException("文件格式转换出错");
        }
    }
    
    
    
    /**
     * 图片格式转化操作返回BufferedImage对象
     * 
     * @param bufferedImage    
     *                     BufferedImage图片转换对象
     * @param format
     *                     待转换的格式 jpeg,gif,png,bmp等
     * @param destPath
     *                     目标文件地址
     * @throws Exception
     */
    public static void formatConvert(BufferedImage bufferedImag, String format, String destPath)throws Exception{
        try {
            ImageIO.write(bufferedImag, format, new File(destPath));
        } catch (IOException e) {
            throw new RuntimeException("文件格式转换出错");
        }
    }
    
    
    /**
     * 获取图片文件的真实格式信息
     * 
     * @param imgPath
     *                     图片原文件存放地址
     * @return
     *                     图片格式
     * @throws Exception
     */
    public static String imageFormat(String imgPath)throws Exception{
        String[] filess = imgPath.split("\\\\");
        String[] formats = filess[filess.length - 1].split("\\.");
        return formats[formats.length - 1];
     }

	/**
	 * 图片设置圆角
	 * 
	 * @param srcImage
	 * @param radius
	 * @param border
	 * @param padding
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage setRadius(BufferedImage srcImage, int radius, int border, int padding) throws IOException {
		int width = srcImage.getWidth();
		int height = srcImage.getHeight();
		int canvasWidth = width + padding * 2;
		int canvasHeight = height + padding * 2;

		BufferedImage image = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D gs = image.createGraphics();
		gs.setComposite(AlphaComposite.Src);
		gs.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gs.setColor(Color.WHITE);
		gs.fill(new RoundRectangle2D.Float(0, 0, canvasWidth, canvasHeight, radius, radius));
		gs.setComposite(AlphaComposite.SrcAtop);
		gs.drawImage(setClip(srcImage, radius), padding, padding, null);
		if (border != 0) {
			gs.setColor(Color.WHITE);
			gs.setStroke(new BasicStroke(border));
			gs.drawRoundRect(padding, padding, canvasWidth - 2 * padding, canvasHeight - 2 * padding, radius, radius);
		}
		gs.dispose();
		return image;
	}

	/**
	 * 图片切圆角
	 * 
	 * @param srcImage
	 * @param radius
	 * @return
	 */
	public static BufferedImage setClip(BufferedImage srcImage, int radius) {
		int width = srcImage.getWidth();
		int height = srcImage.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D gs = image.createGraphics();
		gs.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gs.setClip(new RoundRectangle2D.Double(0, 0, width, height, radius, radius));
		gs.drawImage(srcImage, 0, 0, null);
		gs.dispose();
		return image;
	}

	public static BufferedImage setRadius(BufferedImage srcImage) throws IOException {
		int radius = (srcImage.getWidth() + srcImage.getHeight()) / 6;
		return setRadius(srcImage, radius, 2, 5);
	}

	/***
	 *  * 按指定的比例缩放图片  *  * @param sourceImagePath  *   源地址  * @param
	 * destinationPath  *   改变大小后图片的地址  * @param scale  *   缩放比例，如1.2  
	 */
	public static void scaleImage(String sourceImagePath, String destinationPath, double scale, String format) {

		File file = new File(sourceImagePath);
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(file);
			int width = bufferedImage.getWidth();
			int height = bufferedImage.getHeight();

			width = parseDoubleToInt(width * scale);
			height = parseDoubleToInt(height * scale);

			Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics graphics = outputImage.getGraphics();
			graphics.drawImage(image, 0, 0, null);
			graphics.dispose();

			ImageIO.write(outputImage, format, new File(destinationPath));
		} catch (IOException e) {
			System.out.println("scaleImage方法压缩图片时出错了");
			e.printStackTrace();
		}

	}

	/***
	 *  * 将图片缩放到指定的高度或者宽度  * @param sourceImagePath 图片源地址  * @param
	 * destinationPath 压缩完图片的地址  * @param width 缩放后的宽度  * @param height 缩放后的高度
	 *  * @param auto 是否自动保持图片的原高宽比例  * @param format 图图片格式 例如 jpg  
	 */
	public static void scaleImageWithParams(String sourceImagePath, String destinationPath, int width, int height, boolean auto, String format) {

		try {
			File file = new File(sourceImagePath);
			BufferedImage bufferedImage = null;
			bufferedImage = ImageIO.read(file);
			if (auto) {
				ArrayList<Integer> paramsArrayList = getAutoWidthAndHeight(bufferedImage, width, height);
				width = paramsArrayList.get(0);
				height = paramsArrayList.get(1);
				System.out.println("自动调整比例，width=" + width + " height=" + height);
			}

			Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
			BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics graphics = outputImage.getGraphics();
			graphics.drawImage(image, 0, 0, null);
			graphics.dispose();
			ImageIO.write(outputImage, format, new File(destinationPath));
		} catch (Exception e) {
			System.out.println("scaleImageWithParams方法压缩图片时出错了");
			e.printStackTrace();
		}

	}

	/**
	 *  * 将double类型的数据转换为int，四舍五入原则  *  * @param sourceDouble  * @return  
	 */
	private static int parseDoubleToInt(double sourceDouble) {
		int result = 0;
		result = (int) sourceDouble;
		return result;
	}

	/***
	 *  *  * @param bufferedImage 要缩放的图片对象  * @param width_scale 要缩放到的宽度  * @param
	 * height_scale 要缩放到的高度  * @return 一个集合，第一个元素为宽度，第二个元素为高度  
	 */
	private static ArrayList<Integer> getAutoWidthAndHeight(BufferedImage bufferedImage, int width_scale, int height_scale) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		double scale_w = getDot2Decimal(width_scale, width);

		System.out.println("getAutoWidthAndHeight width=" + width + "scale_w=" + scale_w);
		double scale_h = getDot2Decimal(height_scale, height);
		if (scale_w < scale_h) {
			arrayList.add(parseDoubleToInt(scale_w * width));
			arrayList.add(parseDoubleToInt(scale_w * height));
		} else {
			arrayList.add(parseDoubleToInt(scale_h * width));
			arrayList.add(parseDoubleToInt(scale_h * height));
		}
		return arrayList;

	}

	/***
	 *  * 返回两个数a/b的小数点后三位的表示  * @param a  * @param b  * @return  
	 */
	public static double getDot2Decimal(int a, int b) {

		BigDecimal bigDecimal_1 = new BigDecimal(a);
		BigDecimal bigDecimal_2 = new BigDecimal(b);
		BigDecimal bigDecimal_result = bigDecimal_1.divide(bigDecimal_2, new MathContext(4));
		Double double1 = new Double(bigDecimal_result.toString());
		System.out.println("相除后的double为：" + double1);
		return double1;
	}

	/**
	 * * 图片文件读取
	 * 
	 * @param srcImgPath
	 * @return
	 */
	private static BufferedImage InputImage(String srcImgPath) throws RuntimeException {

		BufferedImage srcImage = null;
		FileInputStream in = null;
		try {
			// 构造BufferedImage对象
			File file = new File(srcImgPath);
			in = new FileInputStream(file);
			byte[] b = new byte[5];
			in.read(b);
			srcImage = javax.imageio.ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("读取图片文件出错！", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					throw new RuntimeException("读取图片文件出错！", e);
				}
			}
		}
		return srcImage;
	}

	/**
	 * * 将图片按照指定的图片尺寸、源图片质量压缩(默认质量为1)
	 * 
	 * @param srcImgPath
	 *            :源图片路径
	 * @param outImgPath
	 *            :输出的压缩图片的路径
	 * @param new_w
	 *            :压缩后的图片宽
	 * @param new_h
	 *            :压缩后的图片高
	 */
	public static void resize(String srcImgPath, String outImgPath, int new_w, int new_h) {
		resize(srcImgPath, outImgPath, new_w, new_h, 1F);
	}

	/**
	 * 将图片按照指定的尺寸比例、源图片质量压缩(默认质量为1)
	 * 
	 * @param srcImgPath
	 *            :源图片路径
	 * @param outImgPath
	 *            :输出的压缩图片的路径
	 * @param ratio
	 *            :压缩后的图片尺寸比例
	 * @param per
	 *            :百分比
	 */
	public static void resize(String srcImgPath, String outImgPath, float ratio) {
		resize(srcImgPath, outImgPath, ratio, 1F);
	}

	/**
	 * 将图片按照指定长或者宽的最大值来压缩图片(默认质量为1)
	 * 
	 * @param srcImgPath
	 *            :源图片路径
	 * @param outImgPath
	 *            :输出的压缩图片的路径
	 * @param maxLength
	 *            :长或者宽的最大值
	 * @param per
	 *            :图片质量
	 */
	public static void resize(String srcImgPath, String outImgPath, int maxLength) {
		resize(srcImgPath, outImgPath, maxLength, 1F);
	}

	/**
	 * * 将图片按照指定的图片尺寸、图片质量压缩
	 * 
	 * @param srcImgPath
	 *            :源图片路径
	 * @param outImgPath
	 *            :输出的压缩图片的路径
	 * @param new_w
	 *            :压缩后的图片宽
	 * @param new_h
	 *            :压缩后的图片高
	 * @param per
	 *            :百分比
	 * @author cevencheng
	 */
	public static void resize(String srcImgPath, String outImgPath, int new_w, int new_h, float per) {
		// 得到图片
		BufferedImage src = InputImage(srcImgPath);
		int old_w = src.getWidth();
		// 得到源图宽
		int old_h = src.getHeight();
		// 得到源图长
		// 根据原图的大小生成空白画布
		BufferedImage tempImg = new BufferedImage(old_w, old_h, BufferedImage.TYPE_INT_RGB);
		// 在新的画布上生成原图的缩略图
		Graphics2D g = tempImg.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, old_w, old_h);
		g.drawImage(src, 0, 0, old_w, old_h, Color.white, null);
		g.dispose();
		BufferedImage newImg = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
		newImg.getGraphics().drawImage(tempImg.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0, 0, null);
		// 调用方法输出图片文件
		outImage(outImgPath, newImg, per);
	}

	public static BufferedImage resize(BufferedImage src, int new_w, int new_h) {
		int old_w = src.getWidth();
		// 得到源图宽
		int old_h = src.getHeight();
		// 得到源图长
		// 根据原图的大小生成空白画布
		BufferedImage tempImg = new BufferedImage(old_w, old_h, BufferedImage.TYPE_INT_RGB);
		// 在新的画布上生成原图的缩略图
		Graphics2D g = tempImg.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, old_w, old_h);
		g.drawImage(src, 0, 0, old_w, old_h, Color.white, null);
		g.dispose();
		BufferedImage newImg = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
		newImg.getGraphics().drawImage(tempImg.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0, 0, null);
		return newImg;
	}

	/**
	 * * 将图片按照指定的尺寸比例、图片质量压缩
	 * 
	 * @param srcImgPath
	 *            :源图片路径
	 * @param outImgPath
	 *            :输出的压缩图片的路径
	 * @param ratio
	 *            :压缩后的图片尺寸比例
	 * @param per
	 *            :百分比
	 * @author cevencheng
	 */
	public static void resize(String srcImgPath, String outImgPath, float ratio, float per) {
		// 得到图片
		BufferedImage src = InputImage(srcImgPath);
		int old_w = src.getWidth();
		// 得到源图宽
		int old_h = src.getHeight();
		// 得到源图长
		int new_w = 0;
		// 新图的宽
		int new_h = 0;
		// 新图的长
		BufferedImage tempImg = new BufferedImage(old_w, old_h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = tempImg.createGraphics();
		g.setColor(Color.white);
		// 从原图上取颜色绘制新图g.fillRect(0, 0, old_w, old_h);
		g.drawImage(src, 0, 0, old_w, old_h, Color.white, null);
		g.dispose();
		// 根据图片尺寸压缩比得到新图的尺寸new_w = (int) Math.round(old_w * ratio);
		new_h = (int) Math.round(old_h * ratio);
		BufferedImage newImg = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
		newImg.getGraphics().drawImage(tempImg.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0, 0, null);
		// 调用方法输出图片文件OutImage(outImgPath, newImg, per);
	}

	/**
	 * <b> 指定长或者宽的最小值来压缩图片 推荐使用此方法 </b>
	 * 
	 * @param srcImgPath
	 *            :源图片路径
	 * @param outImgPath
	 *            :输出的压缩图片的路径
	 * @param maxLength
	 *            :长或者宽的最大值
	 * @param per
	 *            :图片质量
	 * @author cevencheng
	 */
	public static String resize(String srcImgPath, String outImgPath, int maxLength, float per) {
		// 得到图片
		BufferedImage src = InputImage(srcImgPath);
		int old_w = src.getWidth();
		// 得到源图宽
		int old_h = src.getHeight();
		// 得到源图长
		int new_w = 0;
		// 新图的宽
		int new_h = 0;
		// 新图的长
		BufferedImage tempImg = new BufferedImage(old_w, old_h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = tempImg.createGraphics();
		g.setColor(Color.white);
		// 从原图上取颜色绘制新图
		g.fillRect(0, 0, old_w, old_h);
		g.drawImage(src, 0, 0, old_w, old_h, Color.white, null);
		g.dispose();
		// 根据图片尺寸压缩比得到新图的尺寸
		if (old_w < old_h) {
			// 图片要缩放的比例
			new_w = maxLength;
			new_h = (int) Math.round(old_h * ((float) maxLength / old_w));
		} else {
			new_w = (int) Math.round(old_w * ((float) maxLength / old_h));
			new_h = maxLength;
		}
		BufferedImage newImg = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
		newImg.getGraphics().drawImage(tempImg.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0, 0, null);
		// 调用方法输出图片文件
		outImage(outImgPath, newImg, per);
		return new_w + "," + new_h;
	}

	public static Map<String, Integer> getImageSize(String path) {
		File picture = new File(path);
		Map<String, Integer> map = new HashMap<String, Integer>();
		try {
			BufferedImage sourceImg = ImageIO.read(new FileInputStream(picture));
			map.put("width", sourceImg.getWidth());
			map.put("height", sourceImg.getHeight());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public static Map<String, Integer> getImageSize(File picture) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		try {
			BufferedImage sourceImg = ImageIO.read(new FileInputStream(picture));
			map.put("width", sourceImg.getWidth());
			map.put("height", sourceImg.getHeight());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 将图片压缩成指定宽度， 高度等比例缩放
	 * 
	 * @param srcImgPath
	 * @param outImgPath
	 * @param width
	 * @param per
	 */
	public static void resizeFixedWidth(String srcImgPath, String outImgPath, int width, float per) {
		// 得到图片
		BufferedImage src = InputImage(srcImgPath);
		int old_w = src.getWidth();
		// 得到源图宽
		int old_h = src.getHeight();
		// 得到源图长
		int new_w = 0;
		// 新图的宽
		int new_h = 0;
		// 新图的长
		BufferedImage tempImg = new BufferedImage(old_w, old_h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = tempImg.createGraphics();
		g.setColor(Color.white);
		// 从原图上取颜色绘制新图
		g.fillRect(0, 0, old_w, old_h);
		g.drawImage(src, 0, 0, old_w, old_h, Color.white, null);
		g.dispose();
		// 根据图片尺寸压缩比得到新图的尺寸
		if (old_w > old_h) {
			// 图片要缩放的比例
			new_w = width;
			new_h = (int) Math.round(old_h * ((float) width / old_w));
		} else {
			new_w = (int) Math.round(old_w * ((float) width / old_h));
			new_h = width;
		}
		BufferedImage newImg = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
		newImg.getGraphics().drawImage(tempImg.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0, 0, null);
		// 调用方法输出图片文件
		outImage(outImgPath, newImg, per);

	}

	/**
	 * * 将图片文件输出到指定的路径，并可设定压缩质量
	 * 
	 * @param outImgPath
	 * @param newImg
	 * @param per
	 * @author cevencheng
	 */
	private static void outImage(String outImgPath, BufferedImage newImg, float per) {
		// 判断输出的文件夹路径是否存在，不存在则创建
		File file = new File(outImgPath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		// 输出到文件流
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(outImgPath);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
			JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(newImg);
			// 压缩质量
			jep.setQuality(per, true);
			encoder.encode(newImg, jep);
			fos.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	/**
	 * 图片剪切工具方法
	 * 
	 * @param srcfile
	 *            源图片
	 * @param outfile
	 *            剪切之后的图片
	 * @param x
	 *            剪切顶点 X 坐标
	 * @param y
	 *            剪切顶点 Y 坐标
	 * @param width
	 *            剪切区域宽度
	 * @param height
	 *            剪切区域高度
	 * 
	 * @throws IOException
	 * @author cevencheng
	 */
	public static void cut(File srcfile, File outfile, int x, int y, int width, int height) throws IOException {
		FileInputStream is = null;
		ImageInputStream iis = null;
		try {
			// 读取图片文件
			is = new FileInputStream(srcfile);

			/*
			 * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader 声称能够解码指定格式。
			 * 参数：formatName - 包含非正式格式名称 .（例如 "jpeg" 或 "tiff"）等 。
			 */
			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("jpg");
			ImageReader reader = it.next();
			// 获取图片流
			iis = ImageIO.createImageInputStream(is);

			/*
			 * <p>iis:读取源.true:只向前搜索 </p>.将它标记为 ‘只向前搜索’。
			 * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。
			 */
			reader.setInput(iis, true);

			/*
			 * <p>描述如何对流进行解码的类<p>.用于指定如何在输入时从 Java Image I/O
			 * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件 将从其 ImageReader 实现的
			 * getDefaultReadParam 方法中返回 ImageReadParam 的实例。
			 */
			ImageReadParam param = reader.getDefaultReadParam();

			/*
			 * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象
			 * 的左上顶点的坐标（x，y）、宽度和高度可以定义这个区域。
			 */
			Rectangle rect = new Rectangle(x, y, width, height);

			// 提供一个 BufferedImage，将其用作解码像素数据的目标。
			param.setSourceRegion(rect);

			/*
			 * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将 它作为一个完整的
			 * BufferedImage 返回。
			 */
			BufferedImage bi = reader.read(0, param);

			// 保存新图片
			ImageIO.write(bi, "jpg", outfile);
		} finally {
			if (is != null) {
				is.close();
			}
			if (iis != null) {
				iis.close();
			}
		}
	}

}