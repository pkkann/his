
package ISM;

import Control.PersonHandler;
import Control.PersonKatalog;
import View.MainGUI;
import View.OpretPersonGUI;

/**
 *
 * @author patrick
 */
public class ISM {
    
    private PersonKatalog personKatalog;
    private PersonHandler personHandler;
    
    private MainGUI mainGUI;
    private OpretPersonGUI opretPersonGUI;
    
    public ISM() {
        personKatalog = new PersonKatalog();
        personHandler = new PersonHandler(personKatalog);
        
        opretPersonGUI = new OpretPersonGUI(mainGUI, true, personHandler);
        mainGUI = new MainGUI(opretPersonGUI);
    }
    
    public static void main(String[] args) {
        ISM ism = new ISM();
    }

}
