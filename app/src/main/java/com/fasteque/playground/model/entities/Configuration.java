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
        private String[] backdrop_sizes;
        private String[] logo_sizes;
        private String[] poster_sizes;
        private String[] profile_sizes;
        private String[] still_sizes;

        public String getBase_url() {
            return base_url;
        }

        public String getSecure_base_url() {
            return secure_base_url;
        }

        public String[] getBackdrop_sizes() {
            return backdrop_sizes;
        }

        public String[] getLogo_sizes() {
            return logo_sizes;
        }

        public String[] getPoster_sizes() {
            return poster_sizes;
        }

        public String[] getProfile_sizes() {
            return profile_sizes;
        }

        public String[] getStill_sizes() {
            return still_sizes;
        }
    }
}
