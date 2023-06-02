// Generated by view binder compiler. Do not edit!
package com.example.streetcar.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.streetcar.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final CardView btnAgendar;

  @NonNull
  public final CardView btnConfig;

  @NonNull
  public final CardView btnExit;

  @NonNull
  public final CardView btnHome;

  @NonNull
  public final CardView btnPerfil;

  @NonNull
  public final CardView btnServicos;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final ImageView imageView5;

  @NonNull
  public final GridLayout logo;

  private ActivityMainBinding(@NonNull RelativeLayout rootView, @NonNull CardView btnAgendar,
      @NonNull CardView btnConfig, @NonNull CardView btnExit, @NonNull CardView btnHome,
      @NonNull CardView btnPerfil, @NonNull CardView btnServicos, @NonNull ImageView imageView2,
      @NonNull ImageView imageView5, @NonNull GridLayout logo) {
    this.rootView = rootView;
    this.btnAgendar = btnAgendar;
    this.btnConfig = btnConfig;
    this.btnExit = btnExit;
    this.btnHome = btnHome;
    this.btnPerfil = btnPerfil;
    this.btnServicos = btnServicos;
    this.imageView2 = imageView2;
    this.imageView5 = imageView5;
    this.logo = logo;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnAgendar;
      CardView btnAgendar = ViewBindings.findChildViewById(rootView, id);
      if (btnAgendar == null) {
        break missingId;
      }

      id = R.id.btnConfig;
      CardView btnConfig = ViewBindings.findChildViewById(rootView, id);
      if (btnConfig == null) {
        break missingId;
      }

      id = R.id.btnExit;
      CardView btnExit = ViewBindings.findChildViewById(rootView, id);
      if (btnExit == null) {
        break missingId;
      }

      id = R.id.btnHome;
      CardView btnHome = ViewBindings.findChildViewById(rootView, id);
      if (btnHome == null) {
        break missingId;
      }

      id = R.id.btnPerfil;
      CardView btnPerfil = ViewBindings.findChildViewById(rootView, id);
      if (btnPerfil == null) {
        break missingId;
      }

      id = R.id.btnServicos;
      CardView btnServicos = ViewBindings.findChildViewById(rootView, id);
      if (btnServicos == null) {
        break missingId;
      }

      id = R.id.imageView2;
      ImageView imageView2 = ViewBindings.findChildViewById(rootView, id);
      if (imageView2 == null) {
        break missingId;
      }

      id = R.id.imageView5;
      ImageView imageView5 = ViewBindings.findChildViewById(rootView, id);
      if (imageView5 == null) {
        break missingId;
      }

      id = R.id.logo;
      GridLayout logo = ViewBindings.findChildViewById(rootView, id);
      if (logo == null) {
        break missingId;
      }

      return new ActivityMainBinding((RelativeLayout) rootView, btnAgendar, btnConfig, btnExit,
          btnHome, btnPerfil, btnServicos, imageView2, imageView5, logo);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
