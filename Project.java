
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.OutputKeys;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*****************************************************************
 Project handles several important features of the UseCase editor
 program, including adding, removing, and saving UseCases that have
 been created by the User.
 @author Wesley Krug, Gabriel Steponovich,
 Michael Brecker, Halston Raddatz
 @version Winter 2015
 *****************************************************************/
public class Project {
    /**An ArrayList of UseCases that this Project owns.**/
    private ArrayList<UseCase> useCases;

    /**Default value for projectName.**/
    private String projectName = "Project";

    /*****************************************************************
     Builds an ArrayList of UseCase objects.
     *****************************************************************/
    public Project() {
        useCases = new ArrayList<UseCase>();
    }

    /*****************************************************************
     Handles functionality associated with adding a new UseCase object
     to the UseCase array.
     @param uc - UseCase to be added to array.
     *****************************************************************/
    public final void addUsecase(final UseCase uc) {
        for (UseCase uc2 : useCases) {
            if (uc2.getId().equals(uc.getId())) {
                useCases.remove(uc2);
                useCases.add(uc);
                return;
            }
        }
        useCases.add(uc);
    }

    /*****************************************************************
     Returns the UseCase with the requested ID.
     @param id - String representation of UseCase ID being requested.
     @return UseCase usecase of the given id to be returned.
     *****************************************************************/
    public final UseCase getUsecase(final String id) {
        for (UseCase uc : useCases) {
            if (uc.getId().equals(id)) {
                return uc;
            }
        }
        return null;
    }

    /*****************************************************************
     Handles functionality associated with removing a UseCase object
     from the array.
     @param currentUseCase - UseCase to be removed from array.
     @return boolean returns true if remove was successful, false if not.
     *****************************************************************/
    public final boolean removeUsecase(final UseCase currentUseCase) {
        return useCases.remove(currentUseCase);

    }

    /*****************************************************************
     Pulls the IDs from UseCase array for use elsewhere in program.
     @return ids - vector of Strings holding IDs of UseCase objects.
     *****************************************************************/
    public final Vector<String> getIDs() {
        Vector<String> ids = new Vector<String>();
        for (UseCase uc : useCases) {
            ids.add(uc.getId());

        }
        return ids;
    }

    /*****************************************************************
     Allows user to set the name of their project.
     @param name - String representation of desired project name.
     *****************************************************************/
    public final void setProjectName(final String name) {
        projectName = name;
    }

    /*****************************************************************
     Gets the project name of the UseCase currently being accessed.
     @return projectName - String representation of project name.
     *****************************************************************/
    public final String getProjectName() {
        return projectName;
    }

    /*****************************************************************
     Handles save functionality for program to allow operator
     to save their UseCase for future use. Uses XML as primary fileType.
     @param directory - String representation of file directory in which
     the file will be saved.
     *****************************************************************/
    public final void saveToXML(final String directory) {
        try {
            DocumentBuilderFactory docFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //root element(Project)
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("project");
            rootElement.setAttribute("name", this.projectName);
            doc.appendChild(rootElement);
            
            for (UseCase uc : useCases) {
                Element usecase = doc.createElement("usecase");
                rootElement.appendChild(usecase);
                usecase.setAttribute("ID", uc.getId());
                Element name = doc.createElement("name");
                if (!uc.getName().isEmpty()) {
                    name.appendChild(doc.createTextNode(uc.getName()));
                } else {
                    name.appendChild(doc.createTextNode(" "));
                }
                usecase.appendChild((name));
                
                Element id = doc.createElement("id");
                if (!uc.getId().isEmpty()) {
                    id.appendChild(doc.createTextNode(uc.getId()));
                } else {
                    id.appendChild(doc.createTextNode(" "));
                }
                usecase.appendChild((id));
                
                Element desc = doc.createElement("description");
                if (!uc.getDescription().isEmpty()) {
                    desc.appendChild(doc.createTextNode(uc.getDescription()));
                } else {
                    desc.appendChild(doc.createTextNode(" "));
                }
                usecase.appendChild((desc));
                
                Element pActors = doc.createElement("primary-actors");
                if (!uc.getPrimaryActors().isEmpty()) {
                    pActors.appendChild(doc.createTextNode(
                            uc.getPrimaryActors()));
                } else {
                    pActors.appendChild(doc.createTextNode(" "));
                }
                usecase.appendChild((pActors));
                
                Element sActors = doc.createElement("supporting-actors");
                if (!uc.getSupportingActors().isEmpty()) {
                    sActors.appendChild(doc.createTextNode(
                            uc.getSupportingActors()));
                } else {
                    sActors.appendChild(doc.createTextNode(" "));
                }
                usecase.appendChild((sActors));
                
                Element triggers = doc.createElement("trigger");
                if (!uc.getTriggers().isEmpty()) {
                    triggers.appendChild(doc.createTextNode(
                            uc.getTriggers()));
                } else {
                    triggers.appendChild(doc.createTextNode(" "));
                }
                usecase.appendChild((triggers));
                
                Element preConditions = doc.createElement("preconditions");
                if (!uc.getPreConditions().isEmpty()) {
                    preConditions.appendChild(doc.createTextNode(
                            uc.getPreConditions()));
                } else {
                    preConditions.appendChild(doc.createTextNode(" "));
                }
                usecase.appendChild((preConditions));
                
                Element pFlow = doc.createElement("primary-flow");
                if (!uc.getPrimaryFlow().isEmpty()) {
                    pFlow.appendChild(doc.createTextNode(
                            uc.getPrimaryFlow()));
                } else {
                    pFlow.appendChild(doc.createTextNode(" "));
                }
                usecase.appendChild((pFlow));
                
                Element altFlow = doc.createElement("alternate-flow");
                if (!uc.getAlternativeflow().isEmpty()) {
                    altFlow.appendChild(doc.createTextNode(
                            uc.getAlternativeflow()));
                } else {
                    altFlow.appendChild(doc.createTextNode(" "));
                }
                usecase.appendChild((altFlow));
                
                Element minGuarantee = doc.createElement("minimal-guarantee");
                if (!uc.getMinimalGuaruntees().isEmpty()) {
                    minGuarantee.appendChild(doc.createTextNode(
                            uc.getMinimalGuaruntees()));
                } else {
                    minGuarantee.appendChild(doc.createTextNode(" "));
                }
                usecase.appendChild((minGuarantee));
                
                Element sucGuarantee = doc.createElement("success-guarantee");
                if (!uc.getSuccessGuarantees().isEmpty()) {
                    sucGuarantee.appendChild(doc.createTextNode(
                            uc.getSuccessGuarantees()));
                } else {
                    sucGuarantee.appendChild(doc.createTextNode(" "));
                }
                usecase.appendChild((sucGuarantee));
            }
            TransformerFactory transformerFactory =
                    TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(
                    new File(directory + "\\" + this.getProjectName()));
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final String toString() {
        return "Project{"
                + "UseCases=" + useCases
                + ", ProjectName='" + projectName
                + '\'' + '}';
    }
    /*****************************************************************
     Handles load functionality for program to allow operator
     to load their UseCase from a past use. Uses XML as primary fileType.
     @param filename - String representation of file name to be loaded
     *****************************************************************/
    public final void loadFromXML(final String filename) {
        try {
            File file = new File(filename);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            System.out.println("Root element "
                    + doc.getDocumentElement().getNodeName());
            this.setProjectName(doc.getDocumentElement().getAttribute("name"));

            NodeList nodeList = doc.getElementsByTagName("usecase");

            //Grab info to fill into usecase
            for (int i = 0; i < nodeList.getLength(); i++) {
                UseCase u = new UseCase();
                Node fstNode = nodeList.item(i);

                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

                    //Grab Name and set it to u.Name
                    Element element = (Element) fstNode;
                    NodeList nameNmElmtLst
                            = element.getElementsByTagName("name");
                    Element nameNmElmnt
                            = (Element) nameNmElmtLst.item(0);
                    NodeList nameNm
                            = nameNmElmnt.getChildNodes();
                    u.setName(String.valueOf(nameNm.item(0).getNodeValue()));

                    //Grab ID and set it to u.ID
                    NodeList idNmElmntLst = element.getElementsByTagName("id");
                    Element idNmElmnt = (Element) idNmElmntLst.item(0);
                    NodeList idNm = idNmElmnt.getChildNodes();
                    u.setId(String.valueOf(idNm.item(0).getNodeValue()));

                    //Grab Description and set it to u.Description
                    NodeList descNmElmntLst =
                            element.getElementsByTagName("description");
                    Element descNmElmnt =
                            (Element) descNmElmntLst.item(0);
                    NodeList descNm =
                            descNmElmnt.getChildNodes();
                    u.setDescription(String.valueOf(
                            descNm.item(0).getNodeValue()));

                    //Grab Primary Actors and set it to u.primaryActors
                    NodeList pactNmElmntLst
                            = element.getElementsByTagName("primary-actors");
                    Element pactNmElmnt
                            = (Element) pactNmElmntLst.item(0);
                    NodeList pactNm = pactNmElmnt.getChildNodes();
                    u.setPrimaryActors(String.valueOf(
                            pactNm.item(0).getNodeValue()));

                    //Grab Supporting-Actors and set it to u.supportingActors
                    NodeList sActNmElmntLst =
                            element.getElementsByTagName("supporting-actors");
                    Element sActNmElmnt =
                            (Element) sActNmElmntLst.item(0);
                    NodeList sActNm =
                            sActNmElmnt.getChildNodes();
                    u.setSupportingActors(String.valueOf(
                            sActNm.item(0).getNodeValue()));

                    //Grab Trigger and set it to u.Trigger
                    NodeList trigNmElmntLst =
                            element.getElementsByTagName("trigger");
                    Element trigNmElmnt =
                            (Element) trigNmElmntLst.item(0);
                    NodeList trigNm =
                            trigNmElmnt.getChildNodes();
                    u.setTriggers(String.valueOf(
                            trigNm.item(0).getNodeValue()));

                    //Grab Precondition and set it to u.Precondition
                    NodeList preCNmElmntLst =
                            element.getElementsByTagName("preconditions");
                    Element preCNmElmnt =
                            (Element) preCNmElmntLst.item(0);
                    NodeList preCNm =
                            preCNmElmnt.getChildNodes();
                    u.setPreConditions(String.valueOf(
                            preCNm.item(0).getNodeValue()));

                    //Grab Primary Flow and set it to u.primaryFlow
                    NodeList pFlowNmElmntLst =
                            element.getElementsByTagName("primary-flow");
                    Element pFlowNmElmnt =
                            (Element) pFlowNmElmntLst.item(0);
                    NodeList pFlowNm =
                            pFlowNmElmnt.getChildNodes();
                    u.setPrimaryFlow(String.valueOf(
                            pFlowNm.item(0).getNodeValue()));

                    //Grab Alternate Flow and set it to u.alternateFlow
                    NodeList aFlowNmElmntLst =
                            element.getElementsByTagName("alternate-flow");
                    Element aFlowNmElmnt =
                            (Element) aFlowNmElmntLst.item(0);
                    NodeList aFlowNm =
                            aFlowNmElmnt.getChildNodes();
                    u.setAlternativeFlow(String.valueOf(
                            aFlowNm.item(0).getNodeValue()));

                    //Grab Minimal Guarantee and set it to u.minimalGuarantee
                    NodeList mingNmElmntLst =
                            element.getElementsByTagName("minimal-guarantee");
                    Element mingNmElmnt =
                            (Element) mingNmElmntLst.item(0);
                    NodeList mingNm =
                            mingNmElmnt.getChildNodes();
                    u.setMinimalGuarantees(String.valueOf(
                            mingNm.item(0).getNodeValue()));

                    //Grab Success Guarantee and set it to u.successGuarantee
                    NodeList sucGNmElmntLst =
                            element.getElementsByTagName("success-guarantee");
                    Element sucGNmElmnt =
                            (Element) sucGNmElmntLst.item(0);
                    NodeList sucGNm =
                            sucGNmElmnt.getChildNodes();
                    u.setSuccessGuarantees(String.valueOf(
                            sucGNm.item(0).getNodeValue()));
                }
                this.addUsecase(u);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
