package com.centrohospitalar.grupog.utilities;

import javax.servlet.http.HttpServletRequest;

public class Utility {

    public static String getSiteURL (HttpServletRequest request){
        String siteURL = request.getRequestURL().toString();
        //System.out.println(request.getRequestURL());//test purpose to see what form has request.getRequestURL()
        return siteURL.replace(request.getServletPath(),""); //!!!!!!! perguntar ao stor o que é isto
    }
}
