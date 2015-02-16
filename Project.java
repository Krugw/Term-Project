

import java.io.File;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.util.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/*****************************************************************
Project handles several important features of the UseCase editor
program, including adding, removing, and saving UseCases that have
been created by the User.
@authors 
@version Summer 2015
*****************************************************************/
public class Project {
    private ArrayList<UseCase> Usecases;
    private String ProjectName = "Project";

	/*****************************************************************
	Builds an ArrayList of UseCase objects
	*****************************************************************/	
    public Project() {
        Usecases = new ArrayList<UseCase>();
    }

	/*****************************************************************
	Handles functionality associated with adding a new UseCase object
	to the UseCase array
	@param uc - UseCase to be added to array
	*****************************************************************/	
    public void addUsecase(UseCase uc){
    	for (UseCase uc2 : Usecases) {
            if (uc2.getID().equals(uc.getID())) {
            	Usecases.remove(uc2);
            	Usecases.add(uc);
                return;
            }
        }
        Usecases.add(uc);
    }

	/*****************************************************************
	Returns the UseCase with the requested ID
	@param id - String representation of UseCase ID being requested
	*****************************************************************/	
    public UseCase GetUsecase(String id) {
        for (UseCase Usecase : Usecases) {
            if (Usecase.getID().equals(id)) {
                return Usecase;
            }
        }
        return null;
    }
    
	/*****************************************************************
	Handles functionality associated with removing a UseCase object
	from the array. 
	@param currentUseCase - UseCase to be removed from array
	*****************************************************************/	
    public boolean RemoveUsecase(UseCase currentUseCase) {
    	return Usecases.remove(currentUseCase);
    	
    }
    
	/*****************************************************************
	Pulls the IDs from UseCase array for use elsewhere in program
	@return ids - vector of Strings holding IDs of UseCase objects
	*****************************************************************/	
    public Vector<String> Getids() {
    	Vector<String> ids = new Vector<String>();
        for (UseCase Usecase : Usecases) {
        	ids.add(Usecase.getID());
            
        }
        return ids;
    }
    
	/*****************************************************************
	Allows user to set the name of their project
	@param name - String representation of desired project name
	*****************************************************************/	
    public void setProjectName(String name) {
        ProjectName = name;
    }

	/*****************************************************************
	Gets the project name of the UseCase currently being accessed
	@return ProjectName - String representation of project name
	*****************************************************************/	
    public String GetProjectName() {
        return ProjectName;
    }

	/*****************************************************************
	Handles save functionality for program to allow operator
	to save their UseCase for future use. Uses XML as primary filetype.
	@param directory - String representation of file directory in which
						the file will be saved.
	*****************************************************************/	
    public void saveToXML(String directory) {

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //root element(Project)
            Document doc = (Document) docBuilder.newDocument();
            Element rootElement = doc.createElement("project");
            rootElement.setAttribute("name", this.ProjectName);
            doc.appendChild(rootElement);


            //child element(usecase)
            for (UseCase Usecase : Usecases) {
                Element usecase = doc.createElement("usecase");
                rootElement.appendChild(usecase);

                //add attribute to usecase (ID)
                usecase.setAttribute("ID", Usecase.getID());

                //fill in use case information

                //enter name element
                Element name = doc.createElement("name");
                if(Usecase.getName() != "")
                name.appendChild(doc.createTextNode(Usecase.getName()));
                else
                	name.appendChild(doc.createTextNode(" "));
                usecase.appendChild((name));

                //enter ID element
                Element id = doc.createElement("id");
                if(Usecase.getID() != "")
                id.appendChild(doc.createTextNode(Usecase.getID()));
                else
                	id.appendChild(doc.createTextNode(" "));
                usecase.appendChild((id));

                //enter description element
                Element desc = doc.createElement("description");
                if(Usecase.getDescription() != "")
                desc.appendChild(doc.createTextNode(Usecase.getDescription()));
                else
                desc.appendChild(doc.createTextNode(" "));
                usecase.appendChild((desc));

                //enter Primary Actors element
                Element pactors = doc.createElement("primary-actors");
                if(Usecase.getPrimaryActors() != "")
                pactors.appendChild(doc.createTextNode(Usecase.getPrimaryActors()));
                else
                	pactors.appendChild(doc.createTextNode(" "));
                usecase.appendChild((pactors));

                //enter Supporting Actors element
                Element sactors = doc.createElement("supporting-actors");
                if(Usecase.getSupportingActors() != "")
                sactors.appendChild(doc.createTextNode(Usecase.getSupportingActors()));
                else
                	sactors.appendChild(doc.createTextNode(" "));
                usecase.appendChild((sactors));

                //enter Triggers element
                Element triggers = doc.createElement("trigger");
                if(Usecase.getTriggers() != "")
                triggers.appendChild(doc.createTextNode(Usecase.getTriggers()));
                else
                	triggers.appendChild(doc.createTextNode(" "));
                usecase.appendChild((triggers));

                //enter Preconditions element
                Element precondit = doc.createElement("preconditions");
                if(Usecase.getPreconditions()!= "")
                precondit.appendChild(doc.createTextNode(Usecase.getPreconditions()));
                else
                	precondit.appendChild(doc.createTextNode(" "));
                usecase.appendChild((precondit));

                //enter Primary Flow element
                Element pflow = doc.createElement("primary-flow");
                if(Usecase.getPrimaryflow() != "")
                pflow.appendChild(doc.createTextNode(Usecase.getPrimaryflow()));
                else
                    pflow.appendChild(doc.createTextNode(" "));
                usecase.appendChild((pflow));

                //enter Alternate Flow element
                Element altflow = doc.createElement("alternate-flow");
                if(Usecase.getAlternativeflow() != "")
                altflow.appendChild(doc.createTextNode(Usecase.getAlternativeflow()));
                else
                	altflow.appendChild(doc.createTextNode(" "));
                usecase.appendChild((altflow));

                //enter Minimal Guarantees element
                Element minguarantee = doc.createElement("minimal-guarantee");
                if(Usecase.getMinimalGuaruntees() != "")
                minguarantee.appendChild(doc.createTextNode(Usecase.getMinimalGuaruntees()));
                else
                	minguarantee.appendChild(doc.createTextNode(" "));
                usecase.appendChild((minguarantee));

                //enter Success Guarantees element
                Element sucguarantee = doc.createElement("success-guarantee");
                if(Usecase.getSuccessGuarantees() != "")
                sucguarantee.appendChild(doc.createTextNode(Usecase.getSuccessGuarantees()));
                else
                	sucguarantee.appendChild(doc.createTextNode(" "));
                usecase.appendChild((sucguarantee));
            }

            //write content to xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            //make it pretty
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            //make it pretty

            DOMSource source = new DOMSource(doc);

            //********************************NEED TO FIGURE OUT WHERE TO SAVE UNIVERSALLY**************************
            StreamResult result = new StreamResult(new File(directory + "\\"+ this.GetProjectName()));
            //********************************NEED TO FIGURE OUT WHERE TO SAVE UNIVERSALLY**************************

            transformer.transform(source,result);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Project{" +
                "Usecases=" + Usecases +
                ", ProjectName='" + ProjectName + '\'' +
                '}';
    }
	/*****************************************************************
	Handles load functionality for program to allow operator
	to load their UseCase from a past use. Uses XML as primary filetype.
	@param filename - String representation of file name to be loaded
	*****************************************************************/	
    public void loadFromXML(String filename) {
        try {
            File file = new File(filename);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            System.out.println("Root element " + doc.getDocumentElement().getNodeName());
            this.setProjectName(doc.getDocumentElement().getAttribute("name"));

            NodeList nodeList = doc.getElementsByTagName("usecase");

            //Grab info to fill into usecase
            for (int i = 0; i < nodeList.getLength(); i++) {
                UseCase u = new UseCase();
                Node fstNode = nodeList.item(i);

                if(fstNode.getNodeType() == Node.ELEMENT_NODE){

                    //Grab Name and set it to u.Name
                    Element Elmnt = (Element) fstNode;
                    NodeList nameNmElmntLst = Elmnt.getElementsByTagName("name");
                    Element nameNmElmnt = (Element) nameNmElmntLst.item(0);
                    NodeList nameNm = nameNmElmnt.getChildNodes();
                    u.setName(String.valueOf(nameNm.item(0).getNodeValue()));

                    //Grab ID and set it to u.ID
                    NodeList IDNmElmntLst = Elmnt.getElementsByTagName("id");
                    Element IDNmElmnt = (Element) IDNmElmntLst.item(0);
                    NodeList IDNm = IDNmElmnt.getChildNodes();
                    u.setID(String.valueOf(IDNm.item(0).getNodeValue()));

                    //Grab Description and set it to u.Description
                    NodeList descNmElmntLst = Elmnt.getElementsByTagName("description");
                    Element descNmElmnt = (Element) descNmElmntLst.item(0);
                    NodeList descNm = descNmElmnt.getChildNodes();
                    u.setDescription(String.valueOf(descNm.item(0).getNodeValue()));

                    //Grab Primary Actors and set it to u.primaryActors
                    NodeList pactNmElmntLst = Elmnt.getElementsByTagName("primary-actors");
                    Element pactNmElmnt = (Element) pactNmElmntLst.item(0);
                    NodeList pactNm = pactNmElmnt.getChildNodes();
                    u.setPrimaryActors(String.valueOf(pactNm.item(0).getNodeValue()));

                    //Grab Supporting-Actors and set it to u.supportingActors
                    NodeList sactNmElmntLst = Elmnt.getElementsByTagName("supporting-actors");
                    Element sactNmElmnt = (Element) sactNmElmntLst.item(0);
                    NodeList sactNm = sactNmElmnt.getChildNodes();
                    u.setSupportingActors(String.valueOf(sactNm.item(0).getNodeValue()));

                    //Grab Trigger and set it to u.Trigger
                    NodeList trigNmElmntLst = Elmnt.getElementsByTagName("trigger");
                    Element trigNmElmnt = (Element) trigNmElmntLst.item(0);
                    NodeList trigNm = trigNmElmnt.getChildNodes();
                    u.setTriggers(String.valueOf(trigNm.item(0).getNodeValue()));

                    //Grab Precondition and set it to u.Precondition
                    NodeList precNmElmntLst = Elmnt.getElementsByTagName("preconditions");
                    Element precNmElmnt = (Element) precNmElmntLst.item(0);
                    NodeList precNm = precNmElmnt.getChildNodes();
                    u.setPreconditions(String.valueOf(precNm.item(0).getNodeValue()));

                    //Grab Primay Flow and set it to u.primaryFlow
                    NodeList pflowNmElmntLst = Elmnt.getElementsByTagName("primary-flow");
                    Element pflowNmElmnt = (Element) pflowNmElmntLst.item(0);
                    NodeList pflowNm = pflowNmElmnt.getChildNodes();
                    u.setPrimaryflow(String.valueOf(pflowNm.item(0).getNodeValue()));

                    //Grab Alternate Flow and set it to u.alternateFlow
                    NodeList aflowNmElmntLst = Elmnt.getElementsByTagName("alternate-flow");
                    Element aflowNmElmnt = (Element) aflowNmElmntLst.item(0);
                    NodeList aflowNm = aflowNmElmnt.getChildNodes();
                    u.setAlternativeflow(String.valueOf(aflowNm.item(0).getNodeValue()));

                    //Grab Minimal Guarantee and set it to u.minimalGuarantee
                    NodeList mingNmElmntLst = Elmnt.getElementsByTagName("minimal-guarantee");
                    Element mingNmElmnt = (Element) mingNmElmntLst.item(0);
                    NodeList mingNm = mingNmElmnt.getChildNodes();
                    u.setMinimalGuarantees(String.valueOf(mingNm.item(0).getNodeValue()));

                    //Grab Success Guarantee and set it to u.successGuarantee
                    NodeList sucgNmElmntLst = Elmnt.getElementsByTagName("success-guarantee");
                    Element sucgNmElmnt = (Element) sucgNmElmntLst.item(0);
                    NodeList sucgNm = sucgNmElmnt.getChildNodes();
                    u.setSuccessGuarantees(String.valueOf(sucgNm.item(0).getNodeValue()));
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

    //FOR TESTING XML CODE********
    public static void main(String[] args) {
        Project prj = new Project();
            prj.setProjectName("One");

        UseCase u1 = new UseCase();
            u1.setName("tewqrdsa");
            u1.setID("123");
            u1.setDescription("");
            u1.setPrimaryActors("Me");
            u1.setSupportingActors("You");
            u1.setTriggers("Ugh");
            u1.setPreconditions("Life");
            u1.setPrimaryflow("Forward");
            u1.setAlternativeflow("Sideways");
            u1.setMinimalGuarantees("Win");
            u1.setSuccessGuarantees("Death");

        prj.addUsecase(u1);
        prj.saveToXML("C:\\Users\\stepa_000\\Desktop");
        System.out.println("prj: " + prj.toString());

        Project p = new Project();

       p.loadFromXML("C:\\Users\\stepa_000\\Desktop\\one");

        System.out.println("p: " + p.toString());
    }
}
