package viviant.cn.weeklyplan.util;

/**
 * Created by weiwei.huang on 16-5-31.
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ReadXml {
    private String isFirstOpen;
    private String isOpenNotification;

    public String getIsFirstOpen() {
        return isFirstOpen;
    }

    public String getIsOpenNotification() {
        return isOpenNotification;
    }

    public void setIsFirstOpen(String isFirstOpen) {
        this.isFirstOpen = isFirstOpen;
    }

    public void setIsOpenNotification(String isOpenNotification) {
        this.isOpenNotification = isOpenNotification;
    }

    public ReadXml(String fileName){
        DocumentBuilderFactory domfac=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dombuilder=domfac.newDocumentBuilder();
            InputStream is=new FileInputStream(fileName);
            Document doc=dombuilder.parse(is);
            Element root=doc.getDocumentElement();
            NodeList dbinfo=root.getChildNodes();
            if(dbinfo!=null){
                for(int i=0;i<dbinfo.getLength();i++){
                    Node db=dbinfo.item(i);
                    for(Node node=db.getFirstChild();node!=null;node=node.getNextSibling()){
                        if(node.getNodeType()==Node.ELEMENT_NODE){
                            if(node.getNodeName().equals("isFirstOpen")){
                                setIsFirstOpen(node.getFirstChild().getNodeValue());
                            }
                            if(node.getNodeName().equals("isOpenNotification")){
                                setIsOpenNotification(node.getFirstChild().getNodeValue());
                            }
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}