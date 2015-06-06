package com.fasteque.playground.model.entities;

/**
 * Created by danielealtomare on 06/06/15.
 * Project: Playground
 */
public class Configuration {

    // TODO: POJO not complete, more data is returned by the corresponding API.

    private ConfigurationImages images;

    public ConfigurationImages getImages() {
        return images;
    }

    public class ConfigurationImages {
        private String base_url;
        private String secure_base_url;

        public String getBase_url() {
            return base_url;
        }

        public String getSecure_base_url() {
            return secure_base_url;
        }
    }
}
