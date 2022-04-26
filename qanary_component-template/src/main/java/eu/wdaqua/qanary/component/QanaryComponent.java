package eu.wdaqua.qanary.component;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
//import org.apache.jena.query.Query;
//import org.apache.jena.query.QueryExecution;
//import org.apache.jena.query.QueryExecutionFactory;
//import org.apache.jena.query.QueryFactory;
//import org.apache.jena.query.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import eu.wdaqua.qanary.business.QanaryConfigurator;
import eu.wdaqua.qanary.commons.QanaryMessage;
import eu.wdaqua.qanary.commons.QanaryQuestion;
import eu.wdaqua.qanary.commons.QanaryUtils;
import eu.wdaqua.qanary.commons.triplestoreconnectors.QanaryTripleStoreConnector;
import eu.wdaqua.qanary.commons.triplestoreconnectors.QanaryTripleStoreConnectorQanaryInternal;

/**
 * represent the behavior of an annotator following the Qanary methodology
 *
 * @author AnBo
 */
@Component
public abstract class QanaryComponent {

    private static final Logger logger = LoggerFactory.getLogger(QanaryComponent.class);

    // TODO need to be changed
    final String questionUrl = "http://localhost:8080/question/28f56d32-b30a-428d-ac90-79372a6f7625/";

    /**
     * needs to be implemented for any new Qanary component
     */
    public abstract QanaryMessage process(QanaryMessage myQanaryMessage) throws Exception;

    /**
     * fetch raw data for a question
     */
    public String getQuestionRawData() throws ClientProtocolException, IOException {
        /*
		 * @SuppressWarnings("deprecation") HttpClient client = new
		 * DefaultHttpClient(); HttpGet request = new HttpGet(questionUrl +
		 * QanaryConfiguration.questionRawDataUrlSuffix); HttpResponse response
		 * = client.execute(request);
		 * 
		 * // Get the response BufferedReader rd = new BufferedReader(new
		 * InputStreamReader(response.getEntity().getContent()));
		 * 
		 * String rawText = ""; String line = ""; while ((line = rd.readLine())
		 * != null) { rawText.concat(line); }
		 */
        return "";
    }

    /**
     * get Qanary question
     */
    public QanaryQuestion getQuestion() {

        // TODO: fetch from endpoint+ingraph via SPARQL the resource of rdf:type
        // qa:Question

        // TODO: create QanaryQuestion object with question URL and raw data
        // this.getQuestionRawData()

        return null;
    }

    
    
    /**
     * get access to common utilities useful for the Qanary framework --> internal communication
     */
    public QanaryUtils getUtils(QanaryMessage qanaryMessage) {
    	try {
            return new QanaryUtils(qanaryMessage, new QanaryTripleStoreConnectorQanaryInternal(qanaryMessage.getEndpoint()));
		} catch (Exception e) {
			throw new RuntimeException(e); // TODO: not needed --> replace
		}
    }

    /**
     * get access to common utilities useful for the Qanary framework
     */
    public QanaryUtils getUtils(QanaryMessage qanaryMessage, QanaryTripleStoreConnector myQanaryTripleStoreConnector) {
        return new QanaryUtils(qanaryMessage, myQanaryTripleStoreConnector);
    }

    /**
     * get access to a java representation of the question for the Qanary framework --> internal communication
     */
    public QanaryQuestion<String> getQanaryQuestion(QanaryMessage qanaryMessage) {
    	try {
    		return new QanaryQuestion<String>(qanaryMessage, new QanaryTripleStoreConnectorQanaryInternal(qanaryMessage.getEndpoint()));
		} catch (Exception e) {
			throw new RuntimeException(e); // TODO: not needed --> replace
		}
    }

    /**
     * get access to a java representation of the question for the Qanary framework
     */
    public QanaryQuestion<String> getQanaryQuestion(QanaryMessage qanaryMessage, QanaryConfigurator myQanaryConfigurator) {
        return new QanaryQuestion<String>(qanaryMessage, myQanaryConfigurator);
    }

}
