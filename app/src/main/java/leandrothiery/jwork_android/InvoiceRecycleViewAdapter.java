package leandrothiery.jwork_android;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Adapter to list all invoice of jobseeker
 *
 * @author Leandro Thiery
 * @version 06/25/2021
 */
public class InvoiceRecycleViewAdapter extends RecyclerView.Adapter<InvoiceRecycleViewAdapter.InvoiceRVViewHolder> {
    private Context context;
    private ArrayList<Invoice> arrayList;

    public InvoiceRecycleViewAdapter(Context context, ArrayList<Invoice> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public InvoiceRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_invoice, parent, false);
        return new InvoiceRecycleViewAdapter.InvoiceRVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceRVViewHolder holder, int position) {
        Invoice invoice = arrayList.get(position);

        holder.tvInvoiceId.setText(String.valueOf(invoice.getId()));
        holder.tvInvoiceTotalFee.setText(String.valueOf(invoice.getTotalFee()));
        holder.tvInvoiceDate.setText(invoice.getDate());
        holder.tvInvoiceStatus.setText(invoice.getInvoiceStatus());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class InvoiceRVViewHolder extends RecyclerView.ViewHolder {
        TextView tvInvoiceId, tvInvoiceTotalFee, tvInvoiceDate, tvInvoiceStatus;


        public InvoiceRVViewHolder(@NonNull View itemView) {
            super(itemView);
            tvInvoiceId = itemView.findViewById(R.id.item_invoice_id);
            tvInvoiceTotalFee = itemView.findViewById(R.id.item_invoice_total_fee);
            tvInvoiceDate = itemView.findViewById(R.id.item_invoice_date);
            tvInvoiceStatus = itemView.findViewById(R.id.item_invoice_status);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Invoice invoice = arrayList.get(getAdapterPosition());
                    Toast.makeText(context, invoice.toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, SelesaiJobActivity.class);
                    intent.putExtra("Invoice", invoice);
                    context.startActivity(intent);
                }
            });
        }
    }
}
