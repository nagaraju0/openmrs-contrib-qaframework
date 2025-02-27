/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.legacy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.contrib.qaframework.helper.BuildTests;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.DataManagementPage;
import org.openmrs.contrib.qaframework.page.MergePatientsPage;
import org.openmrs.contrib.qaframework.page.PatientVisitsDashboardPage;

public class MergePatientTest extends ReferenceApplicationTestBase {

    TestData.PatientInfo testPatient1;
    TestData.PatientInfo testPatient2;

    @Before
    public void setUp() throws Exception {
        testPatient1 = createTestPatient();
        testPatient2 = createTestPatient();
    }

    @Test
    @Category(BuildTests.class)
    public void mergePatientTest() {
        DataManagementPage dataManagementPage = homePage.goToDataManagement();
        MergePatientsPage mergePatientsPage = dataManagementPage.goToMergePatient();
        mergePatientsPage.enterPatient1(testPatient1.identifier);
        mergePatientsPage.enterPatient2(testPatient2.identifier);
        mergePatientsPage.clickOnContinue();
        mergePatientsPage.clickOnMergePatient();
        PatientVisitsDashboardPage dashboardPage = mergePatientsPage.clickOnContinue();

        assertThat(dashboardPage.getPatientFamilyName(), is(testPatient2.familyName));
    }

    @After
    public void tearDown() throws Exception {
        deletePatient(testPatient1);
    }
}
