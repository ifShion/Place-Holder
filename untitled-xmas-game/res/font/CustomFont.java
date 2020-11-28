package font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * 
 * @autor Nathan
 */
public abstract class CustomFont {
    /**
     * 
     * @param name Nome da fonte
     * @param size Tamanho da fonte
     */

    public static Font getFont(String name,float size){
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File("untitled-xmas-game/res/font/"+name)).deriveFont(size);
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(CustomFont.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
