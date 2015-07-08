/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segurl.filter;

import javax.enterprise.context.RequestScoped;

/**
 *
 * @author vincent.a.lee
 */
@RequestScoped
public class SegurlURLContainer {
    
    private String programName;

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
    
    
}
