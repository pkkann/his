
package ISM;

import View.MainGUI;
import View.OpretPersonGUI;

/**
 *
 * @author patrick
 */
public class ISM {
    
    private MainGUI mainGUI;
    private OpretPersonGUI opretPersonGUI;
    
    public ISM() {
        opretPersonGUI = new OpretPersonGUI(mainGUI, true);
        mainGUI = new MainGUI(opretPersonGUI);
    }
    
    public static void main(String[] args) {
        ISM ism = new ISM();
    }

}
