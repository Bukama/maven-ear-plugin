/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.maven.plugins.ear;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import org.codehaus.plexus.util.WriterFactory;
import org.codehaus.plexus.util.xml.PrettyPrintXMLWriter;
import org.codehaus.plexus.util.xml.XMLWriter;

/**
 * A base class for deployment descriptor file generators.
 *
 * @author <a href="snicoll@apache.org">Stephane Nicoll</a>
 */
abstract class AbstractXmlWriter {

    private final String encoding;

    AbstractXmlWriter(String encoding) {
        this.encoding = encoding;
    }

    Writer initializeWriter(final File destinationFile) throws EarPluginException {
        try {
            return WriterFactory.newXmlWriter(destinationFile);
        } catch (IOException ex) {
            throw new EarPluginException("Exception while opening file[" + destinationFile.getAbsolutePath() + "]", ex);
        }
    }

    XMLWriter initializeXmlWriter(final Writer writer, final String docType) {
        return new PrettyPrintXMLWriter(writer, encoding, docType);
    }
}
