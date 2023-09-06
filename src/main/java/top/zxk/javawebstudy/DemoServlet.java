package top.zxk.javawebstudy;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import static java.lang.System.out;

@WebServlet("/demo")
public class DemoServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        out.println("DemoServlet init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("application/json;charset=utf-8");
//        PrintWriter out = resp.getWriter();
//        String jsonString = """
//                {
//                "name":"zxk",
//                "age":19
//                }""";
//        out.print(jsonString);
//        out.flush();
//        out.close();

        //返回一个验证码
        Random random = new Random();
        int len = 4;
        int fontSize = 28;
        int width = len * fontSize + 10;
        int height = 50;
        String code = getCode();
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();
        //设置背景色
        g.setColor(Color.WHITE);
        //绘制填充矩形
        g.fillRect(0,0,width,height);
        //绘制干扰线
        for (int i = 0; i <100; i++){
            g.setColor(new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
            int x1 = new Random().nextInt(width);
            int y1 = new Random().nextInt(height);
            int x2 = new Random().nextInt(width);
            int y2 = new Random().nextInt(height);
            g.drawLine(x1,y1,x2,y2);
        }
        //绘制验证码
        g.setColor(Color.yellow);
        g.setFont(new Font("宋体",Font.BOLD,fontSize));
        for (int i = 0; i < len; i++){
            g.setColor(new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
            char c = code.charAt(i);
            g.drawString(c + "", i * fontSize + 5,fontSize);
        }
        //输出验证码
        ImageIO.write(bufferedImage,"jpg",resp.getOutputStream());
    }


    private  String getCode(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++){
            int r = random.nextInt(10);
            sb.append(r);
        }
        return sb.toString();
    }
    @Override
    public void destroy() {

        out.println("DemoServlet destory");
    }
}
