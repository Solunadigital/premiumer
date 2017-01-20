package io.github.tslamic.prem;

import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Callback interface responding to {@link Premiumer} events. All methods are invoked
 * on the main thread.
 *
 * @see SimplePremiumerListener
 */
@MainThread public interface PremiumerListener {
  /**
   * Invoked if the sku has not yet been purchased and ads should be visible.
   */
  void onShowAds();

  /**
   * Invoked if the sku has been purchased and ads should not be visible.
   */
  void onHideAds();

  /**
   * Invoked if In-app Billing is unavailable.
   */
  void onBillingUnavailable();

  /**
   * Invoked when {@link SkuDetails} information is ready.
   *
   * @param details the {@link SkuDetails} class or {@code null}, if an error occurred.
   */
  void onSkuDetails(@Nullable SkuDetails details);

  /**
   * Invoked when the sku has been successfully consumed.
   */
  void onSkuConsumed();

  /**
   * Invoked when the sku has not been successfully consumed.
   */
  void onFailedToConsumeSku();

  /**
   * Invoked on a successful sku purchase.
   *
   * @param purchase the purchase data.
   */
  void onPurchaseSuccessful(@NonNull Purchase purchase);

  /**
   * Invoked when the sku purchase is unsuccessful.
   *
   * This happens if the Activity.onActivityResult resultCode is not equal to
   * Activity.RESULT_OK.
   *
   * @param resultCode the onActivityResult resultCode value.
   * @param data the onActivityResult data.
   */
  void onPurchaseBadResult(int resultCode, @Nullable Intent data);

  /**
   * Invoked when the sku purchase is unsuccessful.
   *
   * This happens if either onActivityResult data is null, or the billing response is
   * not BILLING_RESPONSE_RESULT_OK.
   *
   * @param data the onActivityResult data, which can be {@code null}.
   */
  void onPurchaseBadResponse(@Nullable Intent data);

  /**
   * Invoked when the sku purchase is successful, but the request payload differs from the
   * purchase payload.
   *
   * Note that even if {@link io.github.tslamic.prem.Premiumer.Builder#autoNotifyAds(boolean)} is
   * {@code true}, {@link #onHideAds()} will NOT be invoked.
   *
   * @param purchase the Purchase data.
   * @param expected the expected token.
   * @param actual the actual token.
   */
  void onPurchaseInvalidPayload(@NonNull Purchase purchase, @NonNull String expected,
      @NonNull String actual);
}
