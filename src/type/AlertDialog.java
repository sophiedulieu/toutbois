package type;

import javafx.scene.control.Alert;
import mainApp.Main;



/**
 * @author	Oupouwaout
 */
public class AlertDialog extends Alert {



	// ************* MAIN APP ************


	private Main mainApp;

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}



	// ********** CONSTRUCTOR *************


	// Warning
	
	/**
	 * Creates and show an alert dialog with informations for the user.
	 * 
	 * @param typeWarning	the cause of the warning
	 */
	public AlertDialog ( TypeWarning typeWarning ) {
		super(AlertType.WARNING);
		
		String title="", header="", content="";

		switch ( typeWarning ) {
		
		case NO_COMPANY_SELECTED :
			title = "Erreur";
			header = "Sélection vide";
			content = "Veuillez sélectionner une entreprise";

		default:
			break;
		}
		
		this.initOwner(mainApp.getPrimaryStage());

		this.setTitle(title);
		this.setHeaderText(header);
		this.setContentText(content);

		this.showAndWait();
	}


	// Error
	
	/**
	 * Creates and show an error dialog with minimal informations for the user.
	 * 
	 * @param typeError	the operation which failed
	 */
	public AlertDialog ( TypeError typeError ) {
		super(AlertType.ERROR);
		
		String title="Echec", header="", content="";

		switch ( typeError ) {

		case CREATE_FILE_ERROR :
			header = "Fichier non créé";
			content = "Le fichier de sauvegarde n'a pas pu être créé";

		case SAVE_DATA_ERROR :
			header = "Données non sauvegardées";
			content = "Les données n'ont pas pu être " 
					+ "sauvegardées dans le fichier spécifié";

		case LOAD_DATA_ERROR :
			header = "Echec du chargement";
			content = "Les données n'ont pas pu être chargées " 
					+ "à partir du fichier spécifié";
			
		case INIT_ROOT_LAYOUT_ERROR :
			header = "Erreur : Page d'accueil";
			content = "Une erreur est survenue lors de l'affichage "
					+ "de la page d'accueil";
			
		case SHOW_CLIENT_VIEW_ERROR :
			header = "Erreur : Vue Client";
			content = "Une erreur est survenue lors de l'affichage "
					+ "de la fenêtre de gestion client";
			
		case SHOW_PROSPECT_VIEW_ERROR :
			header = "Erreur : Vue Prospect";
			content = "Une erreur est survenue lors de l'affichage "
					+ "de la fenêtre de gestion prospect";
			

		default:
			break;
		}
		
		this.initOwner(mainApp.getPrimaryStage());

		this.setTitle(title);
		this.setHeaderText(header);
		this.setContentText(content);

		this.showAndWait();
	}

}
