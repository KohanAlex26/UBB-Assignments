package repository.xml;

import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
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
import javax.xml.transform.OutputKeys;
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

public class SportiveXmlRepository extends InMemoryRepository<Long, Sportive> {
    List<Sportive> sportives;

    public SportiveXmlRepository(Validator<Sportive> validator) {
        super(validator);
        List<Sportive> sportives = loadSportivesFromXml();
        for (Sportive sportive : sportives)
            System.out.println(sportive);
    }

    /**
     * Reads the sportives from the sportives xml into memory.
     */
    private List<Sportive> loadSportivesFromXml() {
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document root = db.parse("data/sportsteam.xml");
            Element sportsteam = root.getDocumentElement();
            NodeList sportiveList = sportsteam.getChildNodes();
            List<Sportive> sportives = new ArrayList<>();
            for (int i = 0; i < sportiveList.getLength(); ++i) {
                if (!(sportiveList.item(i) instanceof Element))
                    continue;
                Sportive sportive = createSportiveFromNode((Element) sportiveList.item(i));
                sportives.add(sportive);
                try {
                    super.save(sportive);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            }
            return sportives;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates a sportive from xml node
     * @param sportiveElement - given DOM Element containing all the sportive attributes.
     * @return a Sportive instance with the attributes from the xml.
     */
    private static Sportive createSportiveFromNode(Element sportiveElement) {
        Sportive sportive = new Sportive();
//        System.out.println(sportiveElement.get);
        sportive.setId(Long.parseLong(sportiveElement.getElementsByTagName("id").item(0).getTextContent()));
        sportive.setLastName(sportiveElement.getElementsByTagName("lastName").item(0).getTextContent());
        sportive.setFirstName(sportiveElement.getElementsByTagName("firstName").item(0).getTextContent());
        sportive.setAge(Integer.parseInt(sportiveElement.getElementsByTagName("age").item(0).getTextContent()));
        sportive.setTeamId(Integer.parseInt(sportiveElement.getElementsByTagName("teamId").item(0).getTextContent()));
        return sportive;
    }

    @Override
    public Optional<Sportive> save(Sportive entity) throws ValidatorException{
        Optional<Sportive> optional = super.save(entity);
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
     * Save a sportive into xml.
     * @param sportive - given Sportive
     */
    public static void saveToXml(Sportive sportive) throws ParserConfigurationException, IOException, SAXException, TransformerException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("data/sportsteam.xml");

        addSportiveToDom(sportive, document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        // transformer.transform(document, new File("data/bookstore_2.xml"));
        transformer.transform(
                new DOMSource(document),
                new StreamResult(new File("data/sportsteam.xml"))
        );
    }

    private static void addSportiveToDom(Sportive sportive, Document document) {
        Element root = document.getDocumentElement();
        Node sportiveNode = createNodeFromSportive(sportive, document);
        root.appendChild(sportiveNode);
    }

    /**
     * Creates a xml node from a Sportive entity
     * @param sportive - given Sportive
     * @param document - given XML file
     * @return Node to be appended in a XML file.
     */
    private static Node createNodeFromSportive(Sportive sportive, Document document) {
        Element sportiveElement = document.createElement("sportive");

        addChildWithTextContent(document, sportiveElement, "id", Long.toString(sportive.getId()));
        addChildWithTextContent(document, sportiveElement, "lastName", sportive.getLastName());
        addChildWithTextContent(document, sportiveElement, "firstName", sportive.getFirstName());
        addChildWithTextContent(document, sportiveElement, "age", Integer.toString(sportive.getAge()));
        addChildWithTextContent(document, sportiveElement, "teamId", Integer.toString(sportive.getTeamId()));
        return sportiveElement;
    }

    private static void addChildWithTextContent(Document document, Element parent, String tagName, String textContent) {
        Element childElement = document.createElement(tagName);
        childElement.setTextContent(textContent);
        parent.appendChild(childElement);
    }

    @Override
    public Optional<Sportive> delete(Long id){
        try{
            deleteFromXml(id);
        }catch(ParserConfigurationException | IOException | SAXException | TransformerException ex) {
        }
        Optional<Sportive> optional = super.delete(id);
        if(optional.isPresent()){
            return optional;
        }
        return Optional.empty();
    }

    /**
     * Deletes a sportive from xml based on id.
     * @param id must not be null.
     */
    private void deleteFromXml(Long id) throws ParserConfigurationException, IOException, SAXException, TransformerException
    {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("data/sportsteam.xml");


//        removeSportiveFromDom(id,document);
        NodeList list = document.getElementsByTagName("sportive");
        for(int i = 0; i < list.getLength(); i++){
            Element stElement = (Element)list.item(i);
            Element idElement = (Element)stElement.getElementsByTagName("id").item(0);
            Long idValue = Long.parseLong(idElement.getTextContent());
            if(id.equals(idValue)){
                stElement.getParentNode().removeChild(stElement);
            }
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(
                new DOMSource(document),
                new StreamResult(new File("data/sportsteam.xml"))
        );
    }

//    private void removeSportiveFromDom(Long id, Document document) {
//        Optional<Sportive> sportive1=findOne(id);
//        Element root = document.getDocumentElement();
//        Node sportiveNode = createNodeFromSportive(sportive1.get(), document);
//        Element stElement = (Element)sportiveNode;
//        root.removeChild(sportiveNode);
//    }

    @Override
    public Optional<Sportive> update(Sportive sportive){
        try{
            deleteFromXml(sportive.getId());
            saveToXml(sportive);
        }catch(ParserConfigurationException | IOException | SAXException | TransformerException ex) {
        }
        Optional<Sportive> optional = super.update(sportive);
        if(optional.isPresent()){
            return optional;
        }
        return Optional.empty();
    }
}
