package org.manahan.tech.trackyourshipment;

/**
 * Created by dekst on 01/05/2017.
 */

public interface IUtility {

    String trackMyShipmentNow(String[] shipmentIDs, String accessToken);
    String loginRegisteredUser(String userName, String password);

}
