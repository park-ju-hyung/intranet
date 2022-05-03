<<<<<<< HEAD
function getXMLHttpRequest(){
        if(window.ActiveXObject){
              try{
                    return new ActiveXObject("Msxml2.XMLHTTP");
              }catch(e){
                     try{
                           return new ActiveXObject("Microsoft.XML");
                     }catch(e1){
                            return null;
                     }
              }    
        }else if(window.XMLHttpRequest){
              return new XMLHttpRequest();
        }else{
              return null;
        }
=======
function getXMLHttpRequest(){
        if(window.ActiveXObject){
              try{
                    return new ActiveXObject("Msxml2.XMLHTTP");
              }catch(e){
                     try{
                           return new ActiveXObject("Microsoft.XML");
                     }catch(e1){
                            return null;
                     }
              }    
        }else if(window.XMLHttpRequest){
              return new XMLHttpRequest();
        }else{
              return null;
        }
>>>>>>> branch 'master' of https://github.com/inikorea/ini-home2022.git
 }