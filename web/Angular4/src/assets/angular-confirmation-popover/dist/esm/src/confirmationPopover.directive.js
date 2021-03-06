import { Directive, Input, Output, EventEmitter, HostListener, ViewContainerRef, ElementRef, ReflectiveInjector, ComponentFactoryResolver, Inject, Renderer2 } from '@angular/core';
import { DOCUMENT } from '@angular/platform-browser';
import { ConfirmationPopoverWindow } from './confirmationPopoverWindow.component';
import { ConfirmationPopoverOptions, ConfirmationPopoverWindowOptions } from './confirmationPopoverOptions.provider';
import { Positioning } from 'positioning';
/**
 * All properties can be set on the directive as attributes like so (use `ConfirmationPopoverModule.forRoot()` to configure them globally):
 * ```html
 * &lt;button
 *  class="btn btn-default"
 *  mwlConfirmationPopover
 *  [title]="title"
 *  [message]="message"
 *  placement="left"
 *  (confirm)="confirmClicked = true"
 *  (cancel)="cancelClicked = true"
 *  [(isOpen)]="isOpen"&gt;
 *   Show confirm popover!
 * &lt;/button&gt;
 * ```
 */
var ConfirmationPopover = (function () {
    /**
     * @private
     */
    function ConfirmationPopover(viewContainerRef, elm, defaultOptions, cfr, position, renderer, document //tslint:disable-line
    ) {
        this.viewContainerRef = viewContainerRef;
        this.elm = elm;
        this.defaultOptions = defaultOptions;
        this.cfr = cfr;
        this.position = position;
        this.renderer = renderer;
        this.document = document; //tslint:disable-line
        /**
         * Whether to disable showing the popover. Default `false`.
         */
        this.isDisabled = false;
        /**
         * Will open or show the popover when changed.
         * Can be sugared with `isOpenChange` to emulate 2-way binding like so `[(isOpen)]="isOpen"`
         */
        this.isOpen = false;
        /**
         * Will emit when the popover is opened or closed
         */
        this.isOpenChange = new EventEmitter(true);
        /**
         * An expression that is called when the confirm button is clicked.
         */
        this.confirm = new EventEmitter();
        /**
         * An expression that is called when the cancel button is clicked.
         */
        this.cancel = new EventEmitter();
        /**
         * @private
         */
        this.popover = null;
        /**
         * @private
         */
        this.eventListeners = [];
    }
    /**
     * @private
     */
    ConfirmationPopover.prototype.ngOnInit = function () {
        this.isOpenChange.emit(false);
    };
    /**
     * @private
     */
    ConfirmationPopover.prototype.ngOnChanges = function (changes) {
        if (changes.isOpen) {
            if (changes.isOpen.currentValue === true) {
                this.showPopover();
            }
            else {
                this.hidePopover();
            }
        }
    };
    /**
     * @private
     */
    ConfirmationPopover.prototype.ngOnDestroy = function () {
        this.hidePopover();
    };
    /**
     * @private
     */
    ConfirmationPopover.prototype.onConfirm = function (event) {
        this.confirm.emit(event);
        this.hidePopover();
    };
    /**
     * @private
     */
    ConfirmationPopover.prototype.onCancel = function (event) {
        this.cancel.emit(event);
        this.hidePopover();
    };
    /**
     * @private
     */
    ConfirmationPopover.prototype.togglePopover = function () {
        if (!this.popover) {
            this.showPopover();
        }
        else {
            this.hidePopover();
        }
    };
    ConfirmationPopover.prototype.onDocumentClick = function (event) {
        if (this.popover && !this.elm.nativeElement.contains(event.target) && !this.popover.location.nativeElement.contains(event.target)) {
            this.hidePopover();
        }
    };
    ConfirmationPopover.prototype.showPopover = function () {
        var _this = this;
        if (!this.popover && !this.isDisabled) {
            this.eventListeners = [
                this.renderer.listen('document', 'click', function (event) { return _this.onDocumentClick(event); }),
                this.renderer.listen('document', 'touchend', function (event) { return _this.onDocumentClick(event); }),
                this.renderer.listen('window', 'resize', function () { return _this.positionPopover(); })
            ];
            var options_1 = new ConfirmationPopoverWindowOptions();
            Object.assign(options_1, this.defaultOptions, {
                title: this.title,
                message: this.message,
                onConfirm: function (event) {
                    _this.onConfirm(event);
                },
                onCancel: function (event) {
                    _this.onCancel(event);
                },
                onAfterViewInit: function () {
                    _this.positionPopover();
                }
            });
            var optionalParams = [
                'confirmText',
                'cancelText',
                'placement',
                'confirmButtonType',
                'cancelButtonType',
                'focusButton',
                'hideConfirmButton',
                'hideCancelButton',
                'popoverClass',
                'appendToBody',
                'customTemplate'
            ];
            optionalParams.forEach(function (param) {
                if (typeof _this[param] !== 'undefined') {
                    options_1[param] = _this[param];
                }
            });
            var componentFactory = this.cfr.resolveComponentFactory(ConfirmationPopoverWindow);
            var binding = ReflectiveInjector.resolve([{
                    provide: ConfirmationPopoverWindowOptions,
                    useValue: options_1
                }]);
            var contextInjector = this.viewContainerRef.parentInjector;
            var childInjector = ReflectiveInjector.fromResolvedProviders(binding, contextInjector);
            this.popover = this.viewContainerRef.createComponent(componentFactory, this.viewContainerRef.length, childInjector);
            if (options_1.appendToBody) {
                this.document.body.appendChild(this.popover.location.nativeElement);
            }
            this.isOpenChange.emit(true);
        }
    };
    ConfirmationPopover.prototype.positionPopover = function () {
        if (this.popover) {
            var popoverElement = this.popover.location.nativeElement.children[0];
            var popoverPosition = this.position.positionElements(this.elm.nativeElement, popoverElement, this.placement || this.defaultOptions.placement, this.appendToBody || this.defaultOptions.appendToBody);
            this.renderer.setStyle(popoverElement, 'top', popoverPosition.top + "px");
            this.renderer.setStyle(popoverElement, 'left', popoverPosition.left + "px");
        }
    };
    ConfirmationPopover.prototype.hidePopover = function () {
        if (this.popover) {
            this.popover.destroy();
            this.popover = null;
            this.isOpenChange.emit(false);
            this.eventListeners.forEach(function (fn) { return fn(); });
            this.eventListeners = [];
        }
    };
    ConfirmationPopover.decorators = [
        { type: Directive, args: [{
                    selector: '[mwlConfirmationPopover]'
                },] },
    ];
    /** @nocollapse */
    ConfirmationPopover.ctorParameters = function () { return [
        { type: ViewContainerRef, },
        { type: ElementRef, },
        { type: ConfirmationPopoverOptions, },
        { type: ComponentFactoryResolver, },
        { type: Positioning, },
        { type: Renderer2, },
        { type: undefined, decorators: [{ type: Inject, args: [DOCUMENT,] },] },
    ]; };
    ConfirmationPopover.propDecorators = {
        'title': [{ type: Input },],
        'message': [{ type: Input },],
        'confirmText': [{ type: Input },],
        'cancelText': [{ type: Input },],
        'placement': [{ type: Input },],
        'confirmButtonType': [{ type: Input },],
        'cancelButtonType': [{ type: Input },],
        'focusButton': [{ type: Input },],
        'hideConfirmButton': [{ type: Input },],
        'hideCancelButton': [{ type: Input },],
        'isDisabled': [{ type: Input },],
        'isOpen': [{ type: Input },],
        'customTemplate': [{ type: Input },],
        'isOpenChange': [{ type: Output },],
        'confirm': [{ type: Output },],
        'cancel': [{ type: Output },],
        'popoverClass': [{ type: Input },],
        'appendToBody': [{ type: Input },],
        'togglePopover': [{ type: HostListener, args: ['click',] },],
    };
    return ConfirmationPopover;
}());
export { ConfirmationPopover };
//# sourceMappingURL=confirmationPopover.directive.js.map