// Copyright 2017 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.


package com.google.api.ads.adwords.jaxws.v201708.rm;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OfflineDataUploadStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="OfflineDataUploadStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="UNKNOWN"/>
 *     &lt;enumeration value="FAILURE"/>
 *     &lt;enumeration value="IN_PROCESS"/>
 *     &lt;enumeration value="SUCCESS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OfflineDataUploadStatus")
@XmlEnum
public enum OfflineDataUploadStatus {


    /**
     * 
     *                 UNKNOWN value cannot be passed as input.
     *                 <span class="constraint Rejected">Used for return value only. An enumeration could not be processed, typically due to incompatibility with your WSDL version.</span>
     *               
     * 
     */
    UNKNOWN,

    /**
     * 
     *                 Indicates the upload failed in the offline processing.
     *               
     * 
     */
    FAILURE,

    /**
     * 
     *                 Indicates the upload passed formatting checks and was accepted for offline
     *                 processing.
     *               
     * 
     */
    IN_PROCESS,

    /**
     * 
     *                 Indicates the upload was processed by the offline processing pipeline.
     *               
     * 
     */
    SUCCESS;

    public String value() {
        return name();
    }

    public static OfflineDataUploadStatus fromValue(String v) {
        return valueOf(v);
    }

}
