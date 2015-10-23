import java.awt.*;
public class MenuTester {
    public static void main() {
        Frame sara = new Frame("Frame words");
        MenuBar bar = new MenuBar();
        Menu menu = new Menu("Tim's Menu");
        MenuItem item = new MenuItem("Tim's Item");
        menu.add(item);
        bar.add(menu);
        sara.setMenuBar(bar);
        sara.setCursor(Cursor.CROSSHAIR_CURSOR);
        sara.setVisible(true);
    }
}