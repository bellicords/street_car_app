// Generated by view binder compiler. Do not edit!
package com.example.streetcar.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.streetcar.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityViewBookingBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageButton btnBackB;

  @NonNull
  public final RecyclerView recyclerView;

  @NonNull
  public final TextView txtConsultAgend;

  private ActivityViewBookingBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageButton btnBackB, @NonNull RecyclerView recyclerView,
      @NonNull TextView txtConsultAgend) {
    this.rootView = rootView;
    this.btnBackB = btnBackB;
    this.recyclerView = recyclerView;
    this.txtConsultAgend = txtConsultAgend;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityViewBookingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityViewBookingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_view_booking, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityViewBookingBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnBackB;
      ImageButton btnBackB = ViewBindings.findChildViewById(rootView, id);
      if (btnBackB == null) {
        break missingId;
      }

      id = R.id.recyclerView;
      RecyclerView recyclerView = ViewBindings.findChildViewById(rootView, id);
      if (recyclerView == null) {
        break missingId;
      }

      id = R.id.txtConsultAgend;
      TextView txtConsultAgend = ViewBindings.findChildViewById(rootView, id);
      if (txtConsultAgend == null) {
        break missingId;
      }

      return new ActivityViewBookingBinding((ConstraintLayout) rootView, btnBackB, recyclerView,
          txtConsultAgend);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
