/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segurl.filter;

import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vincent.a.lee
 */
//@WebFilter("/program/*")
public class SegurlURLInjectorFilter implements Filter {

    /**
     * This container stores the required parameters for bootstrapping.
     */
    @Inject private SegurlURLContainer urlContainer;
    
    /**
     * 
     */
    private String viewId = "";
    
    public static final String VIEWID_NAME = "viewId";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        viewId = filterConfig.getInitParameter(VIEWID_NAME);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        
        //String viewId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("viewId");
        //ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        //String viewId = ec.getInitParameter("viewId");
        
        String pathInfo = request.getPathInfo();
        String[] pathInfoComp = pathInfo.split("/");
        
        //Get the last element
        String object = (pathInfoComp.length > 0) ? pathInfoComp[pathInfoComp.length-1] : "";
        if(!object.contains(".")){
            // This is not a request for a file resource
            urlContainer.setProgramName(object);
            req.getRequestDispatcher(viewId).forward(req, resp);
            return;
        }
        chain.doFilter(req, resp);
        
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
