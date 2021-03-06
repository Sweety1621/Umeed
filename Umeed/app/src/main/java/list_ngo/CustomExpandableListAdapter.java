package list_ngo;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.onboardingscreen.ChatMainActivity;
import com.example.test.onboardingscreen.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {
    private final Context context;
    private final List<String> listTitulo;
    private final HashMap<String, Contacto> expandableListDetalles;

    public CustomExpandableListAdapter(Context context,
                                       List<String> listTitulo,
                                       HashMap<String, Contacto> expandableListDetalles) {
        this.context = context;
        this.listTitulo = listTitulo;
        this.expandableListDetalles = expandableListDetalles;


    }


    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final Contacto contacto = (Contacto) getChild(groupPosition, childPosition);

        if (convertView == null) {

            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(R.layout.list_item, null);

        }

        CircleImageView circleImageView = convertView.findViewById(R.id.circleIMG);

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), contacto.getImg());
        circleImageView.setImageBitmap(bitmap);

        LinearLayout layoutLlamar = convertView.findViewById(R.id.lLlamar);
        LinearLayout layoutVideollamada = convertView.findViewById(R.id.lVideoLlamada);
        LinearLayout layoutMensaje = convertView.findViewById(R.id.lMensaje);
        LinearLayout layoutInfo = convertView.findViewById(R.id.lInfo);

        layoutLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "CONTACT: "
                        + contacto.getNum(), Toast.LENGTH_SHORT).show();
            }
        });

        layoutMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(),ChatMainActivity.class));
                Toast.makeText(v.getContext(), "MESSAGE: "
                        + contacto.getCor(), Toast.LENGTH_SHORT).show();
            }
        });

        layoutVideollamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "FOLLOW: "
                        + contacto.getNum(), Toast.LENGTH_SHORT).show();
            }
        });

        layoutInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<String> index = new ArrayList<>(expandableListDetalles.keySet());

                Toast.makeText(v.getContext(), "INFO: "
                        + index.get(groupPosition) + " Direccion: "
                        + contacto.getDirection(), Toast.LENGTH_SHORT).show();
            }
        });


        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        convertView.startAnimation(animation);


        return convertView;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {


        String nombre = (String) getGroup(groupPosition);
        Contacto contacto = (Contacto) getChild(groupPosition,0);

        if (convertView == null) {

            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(R.layout.list_group, null);

        }

        TextView txtNombre = convertView.findViewById(R.id.txtGroupNombre);
        TextView txtNumero = convertView.findViewById(R.id.txtGroupNumero);

        txtNombre.setText(nombre);
        txtNumero.setText(contacto.getNum());

        return convertView;
    }








    @Override
    public int getGroupCount() {
        return this.listTitulo.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listTitulo.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childposition) {
        return this.expandableListDetalles.get(this.listTitulo.get(groupPosition));
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childposition) {
        return childposition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }



    @Override
    public boolean isChildSelectable(int groupPosition, int childposition) {
        return true;
    }


}
