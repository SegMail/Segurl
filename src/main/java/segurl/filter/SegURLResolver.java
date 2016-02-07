/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segurl.filter;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author LeeKiatHaw
 */
public class SegURLResolver {
    
    public static final String PATH_DELIMITER = "/";
    
    private List<String> excludes = new ArrayList<String>();
    
    /*
    public static String resolveProgramName(String pathInfo){
        String[] pathInfoComp = splitAndCleanPathInfo(pathInfo);
        
        //Get the last element
        //Right now we don't know if there will be more than 1 parameter in pathinfo
        String programName = (pathInfoComp.length > 0) ? pathInfoComp[pathInfoComp.length-1] : "";
        return (!containsFile(programName)) ? programName : "";
    }*/
    
    /*private static String[] splitAndCleanPathInfo(String pathInfo){
        //If pathInfo is null, just return an empty String array
        if(pathInfo == null) return new String[]{};
        
        String[] allComp = pathInfo.split(PATH_DELIMITER);
        String[] finalAllComp = new String[allComp.length];
        for(int i=0; i<allComp.length; i++){
            finalAllComp[i] = allComp[i].trim();
        }
        return finalAllComp;
    }*/
    
    /*
    public static boolean containsFile(String pathInfo){
        String[] pathInfoComp = splitAndCleanPathInfo(pathInfo);
        String lastComp = (pathInfoComp.length > 0) ? pathInfoComp[pathInfoComp.length-1] : "";
        //It is a file if it contains a . and the position of . is not at the last position
        if(lastComp.contains(".") && lastComp.indexOf(".") < lastComp.length() - 1){
            return true;
        }
        return false;
            
    }*/
    
    public static SegURLResolver getResolver(){
        return new SegURLResolver();
    }

    public SegURLResolver addExclude(String exclude){
        if(excludes == null)
            excludes = new ArrayList<>();
        excludes.add(exclude);
        return this;
    }
    
    /**
     * 
     * @param pathInfo
     * @return 
     */
    public String resolveProgramName(String pathInfo){
        String[] pathInfoComp = splitAndCleanPathInfo(pathInfo);
        
        //Get the last element
        //Right now we don't know if there will be more than 1 parameter in pathinfo
        String programName = (pathInfoComp != null && pathInfoComp.length > 0) ? 
                pathInfoComp[pathInfoComp.length-1] : 
                "";
        return (!containsFile(programName)) ? programName : "";
    }
    
    public boolean containsFile(String pathInfo){
        String[] pathInfoComp = splitAndCleanPathInfo(pathInfo);
        String lastComp = (pathInfoComp.length > 0) ? pathInfoComp[pathInfoComp.length-1] : "";
        //It is a file if it contains a . and the position of . is not at the last position
        if(lastComp.contains(".") 
                && lastComp.indexOf(".") < lastComp.length() - 1
                && !containsExcludes(lastComp)){
            return true;
        }
        return false;
            
    }
    
    public boolean containsExcludes(String pathInfo){
        for(String exclude : excludes){
            if(pathInfo.contains(exclude)) //can be contains() too
                return true;
        }
        return false;
    }
    
    private String[] splitAndCleanPathInfo(String pathInfo){
        //If pathInfo is null, just return an empty String array
        if(pathInfo == null) return new String[]{};
        
        String[] allComp = pathInfo.split(PATH_DELIMITER);
        String[] finalAllComp = new String[allComp.length];
        for(int i=0; i<allComp.length; i++){
            finalAllComp[i] = allComp[i].trim();
        }
        return finalAllComp;
    }
}
