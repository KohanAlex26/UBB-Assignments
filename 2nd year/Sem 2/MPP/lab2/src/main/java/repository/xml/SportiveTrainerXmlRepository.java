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

public class SportiveTrainerXmlRepository extends InMemoryRepository<Long, SportiveTrainer> {
    public SportiveTrainerXmlRepository(Validator<SportiveTrainer> validator){
        super(validator);
        loadSportivesTrainersFromXml();
    }

    /**
     * Reads the sportiveTrainers from the sportiveTrainers xml into memory.
     */
    private List<SportiveTrainer> loadSportivesTrainersFromXml(){
        try{
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document root = db.parse("data/sportivesTrainers.xml");
            Element st = root.getDocumentElement();
            NodeList stList = st.getChildNodes();
            List<SportiveTrainer> sportivesTrainers = new ArrayList<>();
            for(int i = 0; i < stList.getLength(); ++i){
                if(!(stList.item((i)) instanceof Element)){
                    continue;
                }
                SportiveTrainer sportiveTrainer = createSportiveTrainerFromNode((Element) stList.item(i));
                try{
                    super.save(sportiveTrainer);
                }catch(ValidatorException ex){
                    ex.printStackTrace();
                }
                sportivesTrainers.add(sportiveTrainer);
            }
            return sportivesTrainers;
        }catch(ParserConfigurationException e){
            e.printStackTrace();
        }catch (SAXException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<SportiveTrainer> save(SportiveTrainer entity) throws ValidatorException{
        Optional<SportiveTrainer> optional = super.save(entity);
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

    @Override
    public Optional<SportiveTrainer> delete(Long id){
        try{
            deleteFromXml(id);
        }catch(ParserConfigurationException | IOException | SAXException | TransformerException ex) {
            Optional<SportiveTrainer> optional = super.delete(id);
        }
        Optional<SportiveTrainer> optional = super.delete(id);
        if(optional.isPresent()){
            return optional;
        }
        return Optional.empty();
    }

    /**
     * Creates a sportiveTrainer from xml node
     * @param sportiveTrainerElement - given DOM Element containing all the sportiveTrainer attributes.
     * @return a SportiveTrainer instance with the attributes from the xml.
     */
    private static SportiveTrainer createSportiveTrainerFromNode(Element sportiveTrainerElement){
        SportiveTrainer st = new SportiveTrainer();
        st.setId(Long.parseLong(sportiveTrainerElement.getElementsByTagName("id").item(0).getTextContent()));
        st.setSportiveID(Long.parseLong(sportiveTrainerElement.getElementsByTagName("sportiveID").item(0).getTextContent()));
        st.setTrainerID(Long.parseLong(sportiveTrainerElement.getElementsByTagName("trainerID").item(0).getTextContent()));
        st.setTrainingType(sportiveTrainerElement.getElementsByTagName("trainingType").item(0).getTextContent());
        st.setCost(Integer.parseInt(sportiveTrainerElement.getElementsByTagName("cost").item(0).getTextContent()));
        return st;
    }

    /**
     * Save a sportiveTrainer into xml.
     * @param st - given SportiveTrainer
     */
    public static void saveToXml(SportiveTrainer st) throws ParserConfigurationException, IOException, SAXException, TransformerException{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("data/sportivesTrainers.xml");

        addSportiveTrainerToDom(st, document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(
                new DOMSource(document),
                new StreamResult(new File("data/sportivesTrainers.xml"))
        );

    }

    /**
     * Deletes a sportiveTrainer from xml based on id.
     * @param id must not be null.
     */
    private void deleteFromXml(Long id) throws ParserConfigurationException, IOException, SAXException, TransformerException
    {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("data/sportivesTrainers.xml");
        Element table = document.getDocumentElement();
        NodeList list = document.getElementsByTagName("sportiveTrainer");
        for(int i = 0; i < list.getLength(); i++){
            Element stElement = (Element)list.item(i);
            Element idElement = (Element)stElement.getElementsByTagName("id").item(0);
            Long idValue = Long.parseLong(idElement.getTextContent());
            if(id.equals(idValue)){
                stElement.getParentNode().removeChild(stElement);
            }
        }


        //removeSportiveTrainerFromDom(id,document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(
                new DOMSource(document),
                new StreamResult(new File("data/sportivesTrainers.xml"))
        );
    }

    private static void addSportiveTrainerToDom(SportiveTrainer st, Document document){
        Element root = document.getDocumentElement();
        Node sportiveTrainerNode = createNodeFromSportiveTrainer(st, document);

        root.appendChild(sportiveTrainerNode);
    }

    private  void removeSportiveTrainerFromDom(Long id, Document document) {
        Optional<SportiveTrainer> sportiveTrainer = findOne(id);
        Element root = document.getDocumentElement();
        Node sportiveNode = createNodeFromSportiveTrainer(sportiveTrainer.get(), document);
        root.removeChild(sportiveNode);
    }

    /**
     * Creates a xml node from a SportiveTrainer entity
     * @param st - given SportiveTrainer
     * @param document - given XML file
     * @return Node to be appended in a XML file.
     */
    private static Node createNodeFromSportiveTrainer(SportiveTrainer st, Document document){
        Element sportiveTrainerElement = document.createElement("sportiveTrainer");
        addChildWithTextContent(document, sportiveTrainerElement, "id", Long.toString(st.getId()));
        addChildWithTextContent(document, sportiveTrainerElement, "sportiveID", Long.toString(st.getSportiveID()));
        addChildWithTextContent(document, sportiveTrainerElement, "trainerID", Long.toString(st.getTrainerID()));
        addChildWithTextContent(document, sportiveTrainerElement, "trainingType", st.getTrainingType());
        addChildWithTextContent(document, sportiveTrainerElement, "cost", Integer.toString(st.getCost()));

        return sportiveTrainerElement;
    }

    private static void addChildWithTextContent(Document document, Element parent, String tagName, String textContent){
        Element childElement = document.createElement(tagName);
        childElement.setTextContent(textContent);
        parent.appendChild(childElement);
    }
}
