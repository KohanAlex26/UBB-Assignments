package repository.xml;

import domain.entities.Team;
import domain.exceptions.ValidatorException;
import domain.validators.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import repository.InMemoryRepository;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamXmlRepository extends InMemoryRepository<Long, Team> {
    List<Team> teams;

    public TeamXmlRepository(Validator<Team> validator){
        super(validator);
        loadTeamsFromXml();
    }

    /**
     * Reads the teams from the teams xml into memory.
     */
    private List<Team> loadTeamsFromXml(){
        try{
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document root = db.parse("data/teams.xml");
            Element st = root.getDocumentElement();
            NodeList stList = st.getChildNodes();
            List<Team> teams = new ArrayList<>();
            for(int i = 0; i < stList.getLength(); ++i){
                if(!(stList.item((i)) instanceof Element)){
                    continue;
                }
                Team team = createTeamFromNode((Element) stList.item(i));
                try{
                    super.save(team);
                }catch(ValidatorException ex){
                    ex.printStackTrace();
                }
                teams.add(team);
            }
            return teams;
        }catch(ParserConfigurationException e){
            e.printStackTrace();
        }catch (SAXException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates a team from xml node
     * @param teamElement - given DOM Element containing all the team attributes.
     * @return a Team instance with the attributes from the xml.
     */
    private static Team createTeamFromNode(Element teamElement){
        Team team = new Team();
        team.setId(Long.parseLong(teamElement.getElementsByTagName("id").item(0).getTextContent()));
        team.setTeamName(teamElement.getElementsByTagName("teamName").item(0).getTextContent());
        return team;
    }

    @Override
    public Optional<Team> save(Team entity) throws ValidatorException{
        Optional<Team> optional = super.save(entity);
        if(optional.isPresent()){
            return optional;
        }
        try{
            saveToXml(entity);
        }catch(ParserConfigurationException | IOException | SAXException | TransformerException ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Save a team into xml.
     * @param team - given Team
     */
    public static void saveToXml(Team team) throws ParserConfigurationException, IOException, SAXException, TransformerException{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("data/teams.xml");

        addTeamToDom(team, document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(
                new DOMSource(document),
                new StreamResult(new File("data/teams.xml"))
        );
    }

    private static void addTeamToDom(Team team, Document document){
        Element root = document.getDocumentElement();
        Node teamNode = createNodeFromTeam(team, document);

        root.appendChild(teamNode);
    }

    /**
     * Creates a xml node from a Team entity
     * @param team - given Team
     * @param document - given XML file
     * @return Node to be appended in a XML file.
     */
    private static Node createNodeFromTeam(Team team, Document document){
        Element teamElement = document.createElement("team");
        addChildWithTextContent(document, teamElement, "id", Long.toString(team.getId()));
        addChildWithTextContent(document, teamElement, "teamName", team.getTeamName());

        return teamElement;
    }

    private static void addChildWithTextContent(Document document, Element parent, String tagName, String textContent){
        Element childElement = document.createElement(tagName);
        childElement.setTextContent(textContent);
        parent.appendChild(childElement);
    }

    @Override
    public Optional<Team> delete(Long id){
        try{
            deleteFromXml(id);
        }catch(ParserConfigurationException | IOException | SAXException | TransformerException ex) {
            Optional<Team> optional = super.delete(id);
        }
        Optional<Team> optional = super.delete(id);
        if(optional.isPresent()){
            return optional;
        }
        return Optional.empty();
    }

    /**
     * Deletes a team from xml based on id.
     * @param id must not be null.
     */
    private void deleteFromXml(Long id) throws ParserConfigurationException, IOException, SAXException, TransformerException
    {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("data/teams.xml");
        Element table = document.getDocumentElement();
        NodeList list = document.getElementsByTagName("team");
        for(int i = 0; i < list.getLength(); i++){
            Element teamElement = (Element)list.item(i);
            Element idElement = (Element)teamElement.getElementsByTagName("id").item(0);
            Long idValue = Long.parseLong(idElement.getTextContent());
            if(id.equals(idValue)){
                teamElement.getParentNode().removeChild(teamElement);
            }
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(
                new DOMSource(document),
                new StreamResult(new File("data/teams.xml"))
        );
    }
}
