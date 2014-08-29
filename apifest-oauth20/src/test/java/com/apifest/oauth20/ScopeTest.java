/*
 * Copyright 2013-2014, ApiFest project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.apifest.oauth20;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

/**
 * @author Rossitsa Borissova
 */
public class ScopeTest {

    @Test
    public void when_scope_name_length_less_than_2_validate_return_false() throws Exception {
        // GIVEN
        Scope scope = new Scope();
        scope.setScope("");
        scope.setDescription("some descr");
        scope.setCcExpiresIn(200);
        scope.setPassExpiresIn(100);

        // WHEN
        boolean valid = scope.validate();

        // THEN
        assertFalse(valid);
    }

    @Test
    public void when_scope_null_return_false() throws Exception {
        // GIVEN
        Scope scope = new Scope();
        scope.setScope(null);
        scope.setDescription("some descr");
        scope.setCcExpiresIn(200);
        scope.setPassExpiresIn(100);

        // WHEN
        boolean valid = scope.validate();

        // THEN
        assertFalse(valid);
    }

    @Test
    public void when_description_null_return_false() throws Exception {
        // GIVEN
        Scope scope = new Scope();
        scope.setScope("basic");
        scope.setDescription(null);
        scope.setCcExpiresIn(200);
        scope.setPassExpiresIn(100);

        // WHEN
        boolean valid = scope.validate();

        // THEN
        assertFalse(valid);
    }

    @Test
    public void when_cc_expiresIn_0_return_false() throws Exception {
        // GIVEN
        Scope scope = new Scope();
        scope.setScope("basic");
        scope.setDescription("some description");
        scope.setCcExpiresIn(0);
        scope.setPassExpiresIn(100);

        // WHEN
        boolean valid = scope.validate();

        // THEN
        assertFalse(valid);
    }

    @Test
    public void when_pass_expiresIn_0_return_false() throws Exception {
        // GIVEN
        Scope scope = new Scope();
        scope.setScope("basic");
        scope.setDescription("some description");
        scope.setCcExpiresIn(100);
        scope.setPassExpiresIn(0);

        // WHEN
        boolean valid = scope.validate();

        // THEN
        assertFalse(valid);
    }

    @Test
    public void when_all_fields_valid_return_true() throws Exception {
        // GIVEN
        Scope scope = new Scope();
        scope.setScope("basic");
        scope.setDescription("some description");
        scope.setCcExpiresIn(300);
        scope.setPassExpiresIn(100);

        // WHEN
        boolean valid = scope.validate();

        // THEN
        assertTrue(valid);
    }

    @Test
    public void when_load_from_map_return_scope_with_initialized_fields() throws Exception {
        // GIVEN
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("_id", "basic");
        map.put(Scope.DESCRIPTION_FIELD, "some descr");
        map.put(Scope.CC_EXPIRES_IN_FIELD, 300);

        // WHEN
        Scope scope = Scope.loadFromMap(map);

        // THEN
        assertEquals(scope.getScope(), "basic");
        assertEquals(scope.getDescription(), "some descr");
        assertEquals(scope.getCcExpiresIn(), Integer.valueOf(300));
    }

    @Test
    public void when_load_from_string_map_return_scope_with_initialized_fields() throws Exception {
        // GIVEN
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "basic");
        map.put(Scope.DESCRIPTION_FIELD, "some descr");
        map.put(Scope.CC_EXPIRES_IN_FIELD, "300");
        map.put(Scope.PASS_EXPIRES_IN_FIELD, "100");

        // WHEN
        Scope scope = Scope.loadFromStringMap(map);

        // THEN
        assertEquals(scope.getScope(), "basic");
        assertEquals(scope.getDescription(), "some descr");
        assertEquals(scope.getCcExpiresIn(), Integer.valueOf(300));
    }
}