import java.applet.*;
import java.awt.*;
import java.util.*;

public class Clock extends Applet implements Runnable {
Thread t;
Image dbi, cim;
Graphics dbg;
Color bgcolor, arrowscolor, textcolor; 

Font f = new Font ("Helvetica", 0, 16);
Date d; 

int delay=100; 

int w, h; 

int hh, mm, ss; 
int rs=210, rm=240, rh=230; 



int x0=252, y0=261, x1, y1, x2=255, y2=261, x3=262, y3=250, x4=302, y4=298, x5=280, y5=256;
double fim, fih, fis; 


double PI2 = 2*Math.PI;
double fist, fimt;



long t0; 

int d1, d2, d3, d4, d5, d6; 

public void init (){
w = size().width;
h = size().height;
t0 = (new Date()).getTime(); 

dbi = createImage(w, h);
dbg = dbi.getGraphics();
cim = getImage (getCodeBase(), "clock.gif");
bgcolor = parsecolor("BGCOLOR", 0);
textcolor = parsecolor ("TEXTCOLOR", 0);
arrowscolor = parsecolor("ARROWSCOLOR", 0);
}
Color parsecolor(String s, int i){
String s1 = getParameter(s);
Color color = new Color (i);
Color color1;
try{color1 = new Color (Integer.parseInt (s1, 16));}
catch(Exception e) {return color; }
return color1;
}
public void start(){if(t == null) {t = new Thread(this); t.start();}}
public void stop(){if(t != null) {t.stop(); t = null;}}
public void run() {
while(true) {
repaint ();
try{Thread.sleep (delay) ; }
catch (Exception e) {return; }
}
}
public void update(Graphics g) {


Date date = new Date();
hh = date.getHours();
mm = date.getMinutes();
ss = date.getSeconds();
fis = (PI2 * (double)ss) / 60D;
fim = (PI2 * (double)mm + fis) / 60D;
fih = (PI2 * (double) (hh % 12) + fim) / 12D;


dbg.setColor (bgcolor);
dbg.fillRect(0, 0, w, h);
dbg.drawImage(cim, 0, 0, this);
	
	String s="AM";
	if(hh>=12)s="PM";
		hh=hh%12;
		if(hh ==0)hh=12;
		
		dbg.setColor(textcolor);
		d1=hh/10;
		d2=hh%10;
		d3=mm/10;
		d4=mm%10;
		dbg.setFont(f);
		dbg.drawString(d1+ "" +d2 +":" +d3+ "" +d4,x4,y4);
		dbg.drawString(s,x5,y5);
		
//secundar
x1 = (int) ((double)x2 + (double)rs * Math.sin(fis));
y1 = (int) (((double)y2 - (double)rs * Math.cos(fis)));
dbg.setColor(arrowscolor);
dbg.drawLine(x2, y2, x1, y1);
//ingrosare
x1--;
dbg.drawLine(x0, y0, x1, y1);
y1--;
dbg.drawLine(x0, y0, x1, y1);
x1++;
y1++;

x1--;
dbg.drawLine(x0, y0, x1, y1);

//minutar
x1 = (int) ((double)x0 + (double)rm * Math.sin(fim));
y1 = (int) (((double) y0 - (double)rm * Math.cos(fim)));
dbg.drawLine(x0, y0, x1, y1);
dbg.drawLine(x0, y0, x1, y1);
//ingrosare
x1++;
dbg.drawLine(x0, y0, x1, y1);
y1++;
dbg.drawLine(x0, y0, x1, y1);
x1++;
dbg.drawLine(x0, y0, x1, y1);
y1++;
dbg.drawLine(x0, y0, x1, y1);
x1=x1-2;
y1=y1-2;



x1--;
dbg.drawLine(x0, y0, x1, y1);


//orar
x1 = (int) ((double)x0 + (double)rh * Math.sin(fih));
y1 = (int) (((double)y0 - (double)rh * Math.cos(fih)));
dbg.drawLine(x0, y0, x1, y1);
x1++;
dbg.drawLine(x0, y0, x1, y1);
y1++;
dbg.drawLine(x0, y0, x1, y1);
//ingrosare
x1++;
dbg.drawLine(x0, y0, x1, y1);
y1++;
dbg.drawLine(x0, y0, x1, y1);
x1++;
dbg.drawLine(x0, y0, x1, y1);
y1++;
dbg.drawLine(x0, y0, x1, y1);
x1=x1-2;
y1=y1-2;

x1=x1-2;
dbg.drawLine(x0, y0, x1, y1);
y1=y1-2;
dbg.drawLine(x0, y0, x1, y1);
x1--;
dbg.drawLine(x0, y0, x1, y1);
y1--;
dbg.drawLine(x0, y0, x1, y1);
x1=x1+2;
y1=y1+2;


x1--;
dbg.drawLine(x0, y0, x1, y1);


dbg.fillOval(x0 - 2, y0 - 2, 4, 4);
dbg.setColor(arrowscolor.darker());
dbg.drawOval(x0 - 2, y0 - 2, 4, 4);


g.drawImage(dbi, 0, 0, this);
}
}
	