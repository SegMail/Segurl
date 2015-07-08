package segurl.stub;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import segurl.filter.SegurlURLContainer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vincent.a.lee
 */
@RequestScoped
@Named("Bootstrap")
public class BootstrapStub {
    
    @Inject
    private SegurlURLContainer urlContainer;
    
    private String viewRoot;
    
    @PostConstruct
    public void init(){
        System.out.println("Program "+urlContainer.getProgramName()+" called.");
        viewRoot = "/programs/"+urlContainer.getProgramName()+"/layout.xhtml";
    }

    public String getViewRoot() {
        return viewRoot;
    }

    public void setViewRoot(String viewRoot) {
        this.viewRoot = viewRoot;
    }
    
    
}
