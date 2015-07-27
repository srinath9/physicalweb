/*
 * Copyright 2014 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.physical_web.physicalweb;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class OobActivity extends ActionBarActivity {

  View.OnClickListener mAcceptButtonOnClickListener = new View.OnClickListener() {

    @Override
    public void onClick(View v) {
      // Save the opt in preference
      SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.physical_web_preference_file_name), Context.MODE_PRIVATE);
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putBoolean(getString(R.string.user_opted_in_flag), true);
      editor.commit();

      // Exit the activity
      finish();
    }
  };

  private void initializeWebView() {
    WebView webView = (WebView) findViewById(R.id.oob_webview);
    // Force the background color to update (it uses the color specified in the layout xml)
    webView.setBackgroundColor(0x000);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.setWebViewClient(new WebViewClient());
    webView.loadUrl(getString(R.string.url_getting_started));
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_oob);
    findViewById(R.id.oob_accept_button).setOnClickListener(mAcceptButtonOnClickListener);
    initializeWebView();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_oob, menu);
    return true;
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    // Make sure that the back button brings the user to the home screen
    Intent intent = new Intent(Intent.ACTION_MAIN);
    intent.addCategory(Intent.CATEGORY_HOME);
    startActivity(intent);
  }
}
