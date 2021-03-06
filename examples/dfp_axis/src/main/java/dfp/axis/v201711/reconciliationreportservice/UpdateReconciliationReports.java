// Copyright 2015 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package dfp.axis.v201711.reconciliationreportservice;

import com.beust.jcommander.Parameter;
import com.google.api.ads.common.lib.auth.OfflineCredentials;
import com.google.api.ads.common.lib.auth.OfflineCredentials.Api;
import com.google.api.ads.common.lib.utils.examples.CodeSampleParams;
import com.google.api.ads.dfp.axis.factory.DfpServices;
import com.google.api.ads.dfp.axis.utils.v201711.StatementBuilder;
import com.google.api.ads.dfp.axis.v201711.ReconciliationReport;
import com.google.api.ads.dfp.axis.v201711.ReconciliationReportPage;
import com.google.api.ads.dfp.axis.v201711.ReconciliationReportServiceInterface;
import com.google.api.ads.dfp.lib.client.DfpSession;
import com.google.api.ads.dfp.lib.utils.examples.ArgumentNames;
import com.google.api.client.auth.oauth2.Credential;
import com.google.common.collect.Iterables;
import java.util.Arrays;

/**
 * This example updates a reconciliation report's notes. To get all reconciliation reports,
 * run GetAllReconciliationReports.java.
 *
 * Credentials and properties in {@code fromFile()} are pulled from the
 * "ads.properties" file. See README for more info.
 */
public class UpdateReconciliationReports {

  private static class UpdateReconciliationReportsParams extends CodeSampleParams {
    @Parameter(names = ArgumentNames.RECONCILIATION_REPORT_ID, required = true,
        description = "The ID of the reconciliation report to update.")
    private Long reconciliationReportId;
  }

  public static void runExample(
      DfpServices dfpServices, DfpSession session, long reconciliationReportId) throws Exception {
    // Get the ReconciliationReportService.
    ReconciliationReportServiceInterface reconciliationReportService =
        dfpServices.get(session, ReconciliationReportServiceInterface.class);

    // Create a statement to select a single reconciliation report.
    StatementBuilder statementBuilder = new StatementBuilder()
        .where("id = :id")
        .orderBy("id ASC")
        .limit(1)
        .withBindVariableValue("id", reconciliationReportId);

    // Get the reconciliation report.
    ReconciliationReportPage page =
        reconciliationReportService.getReconciliationReportsByStatement(
            statementBuilder.toStatement());

    ReconciliationReport reconciliationReport =
        Iterables.getOnlyElement(Arrays.asList(page.getResults()));

    // Update the notes.
    reconciliationReport.setNotes("Orders still pending review");

    // Update the reconciliation report on the server.
    ReconciliationReport[] updatedReconciliationReports =
        reconciliationReportService.updateReconciliationReports(
            new ReconciliationReport[] {reconciliationReport});

    for (ReconciliationReport updatedReconciliationReport : updatedReconciliationReports) {
      System.out.printf(
          "Reconciliation report with ID %d for month %d/%d was updated.%n",
          updatedReconciliationReport.getId(),
          updatedReconciliationReport.getStartDate().getMonth(),
          updatedReconciliationReport.getStartDate().getYear());
    }
  }

  public static void main(String[] args) throws Exception {
    // Generate a refreshable OAuth2 credential.
    Credential oAuth2Credential = new OfflineCredentials.Builder()
        .forApi(Api.DFP)
        .fromFile()
        .build()
        .generateCredential();

    // Construct a DfpSession.
    DfpSession session = new DfpSession.Builder()
        .fromFile()
        .withOAuth2Credential(oAuth2Credential)
        .build();

    DfpServices dfpServices = new DfpServices();

    UpdateReconciliationReportsParams params = new UpdateReconciliationReportsParams();
    if (!params.parseArguments(args)) {
      // Either pass the required parameters for this example on the command line, or insert them
      // into the code here. See the parameter class definition above for descriptions.
      params.reconciliationReportId = Long.parseLong("INSERT_RECONCILIATION_REPORT_ID_HERE");
    }

    runExample(dfpServices, session, params.reconciliationReportId);
  }
}
